#include <iostream>
#include <string>


#include "BpaDictData.h"
#include "BpaMemDBTable.h"

#include <locale>
#include <QCoreApplication>
#include <QtDebug>
#include <hash_map>
#include <QHash>

#include <QString>
#include <strstream>
#include "bpacards.h"

#include "timecount.h"
#include <QTextStream>

void matchColumns(CardInfo *info, tagMemDBTable *table) {
	info->dbFields.resize(info->fields.size());
	for (int i = 0; i < info->fields.size(); i++) {
		tagBpa_Dict *field = info->fields[i];
		for (int j = 0; j < table->nFieldNum; j++) {
			tagMemDBField* dbField = table->sFieldArray + j;
			if (strcmp(field->szFieldName, dbField->lpszName) == 0) {
				info->dbFields[i] = dbField;
			}
		}
	}
}

int main(int argc, char **argv)
{

	QCoreApplication app(argc, argv);
	QHash<QString, CardInfo> cardType2Card;
	QHash<QString, CardInfo *> table2Card;
	QHash<QString, tagMemDBTable *> table2tableDict;

	int size = sizeof(g_BpaDictArray) / sizeof(tagBpa_Dict);
	for (int i = 0; i < size; i++) {
		const tagBpa_Dict &dic = g_BpaDictArray[i];
		CardInfo *info;
		if (!cardType2Card.contains(dic.szCardKey)) {
			info = &cardType2Card[dic.szCardKey];
			info->name = dic.szCardKey;
			info->tableName = dic.szTable;
			if (dic.bFieldCategory == BpaDatCategory_Dat)
				info->type = DatCard;
			else
				info->type = SwiCard;

			table2Card[dic.szTable] = info;
		}
		else {
			info = &cardType2Card[dic.szCardKey];
		}
		info->fields.push_back(const_cast<tagBpa_Dict *>(&dic));
		std::cout << dic.szFieldName << std::endl;
	}

	

	TC_S("table")
	size = sizeof(g_BpaTableDictArray) / sizeof(tagMemDBTable);
	for (int i = 0; i < size; i++) {
		const tagMemDBTable &table = g_BpaTableDictArray[i];
		qDebug() << table.lpszName << " " << table.lpszDesp << " " << table.lpszAlias;

		for (int j = 0; j < table.nFieldNum; j++) {
			const tagMemDBField &field = table.sFieldArray[j];
			qDebug() << " " << field.lpszName << " " << field.lpszDesp;

		}

		table2tableDict[table.lpszName] = const_cast<tagMemDBTable *>(&table);
	}
	TC_E()

	QHashIterator<QString, CardInfo> it(cardType2Card);
	while (it.hasNext()) {
		it.next();
		CardInfo *c = const_cast<CardInfo *>(&it.value());
		if (!table2tableDict.contains(c->tableName)) {
			qDebug() << "Fail to find card table :" << c->name;
			exit(0);
		}
		tagMemDBTable *table = table2tableDict[c->tableName];
		matchColumns(c, table);
		c->alias = table->lpszDesp;

	}

	std::vector<CardInfo *> cards;
	{
		
		QHashIterator<QString, CardInfo> it(cardType2Card);
		while (it.hasNext()) {
			it.next();
			CardInfo *c = const_cast<CardInfo *>(&it.value());
			cards.push_back(c);

		
			
		}

		TC_S("sort card")
		std::sort(cards.begin(), cards.end(), [](CardInfo *l, CardInfo *r){return strcmp(l->name, r->name) < 0; });
		TC_E()

		std::cout << "*********Card types*****************"<< std::endl;
		
		
		for (int i = 0; i < cards.size(); i++) {
			CardInfo *c = cards[i];
			qDebug() << c->name << " " << QString::fromUtf8(c->alias) << " " << (c->type == DatCard ? "dat" : "swi") << " " << c->tableName;
			
			QString temp;
			QTextStream os(&temp);
			for (int j = 0; j < c->fields.size(); j++) {
				tagBpa_Dict *dic = c->fields.at(j);
				tagMemDBField *dbField = c->dbFields.at(j);
				if (!dbField)
				  os << dic->szFieldName<<" ";
				else
					os << dic->szFieldName <<"("<<QString::fromUtf8(dbField->lpszDesp)<<")"<< " ";
			}
			
			qDebug() <<" fields = "<< temp;
		}
		
	}


	std::cout << "Card type size = "<<cards.size() << std::endl;


	BpaCards bpa(cards);
	TC_S("save card");
	bpa.save();
	TC_E();
	return 0;
}