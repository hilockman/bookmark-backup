#include <iostream>
#include <string>


#include "BpaDictData.h"
#include "BpaMemDBTable.h"
#include <locale>
#include <QCoreApplication>
#include <QtDebug>
#include <hash_map>
#include <QHash>
#include <vector>
#include <QString>

struct CardInfo
{
	const char * name;
	const char * tableName;
	const char * alias;
	std::vector<tagBpa_Dict *> fields;
};
int main(int argc, char **argv)
{

	QCoreApplication app(argc, argv);
	QHash<QString, CardInfo> cardType2Card;
	QHash<QString, CardInfo *> table2Card;
	int size = sizeof(g_BpaDictArray) / sizeof(tagBpa_Dict);
	for (int i = 0; i < size; i++) {
		const tagBpa_Dict &dic = g_BpaDictArray[i];
		CardInfo *info;
		if (!cardType2Card.contains(dic.szCardKey)) {
			info = &cardType2Card[dic.szCardKey];
			info->name = dic.szCardKey;
			info->tableName = dic.szTable;
			table2Card[dic.szTable] = info;
		}
		else {
			info = &cardType2Card[dic.szCardKey];
		}
		info->fields.push_back(const_cast<tagBpa_Dict *>(&dic));
		std::cout << dic.szFieldName << std::endl;
	}


	size = sizeof(g_BpaTableDictArray) / sizeof(tagMemDBTable);
	QHash<QString, tagMemDBTable *> table2tableDict;
	for (int i = 0; i < size; i++) {
		const tagMemDBTable &table = g_BpaTableDictArray[i];
		qDebug() << table.lpszName << " " << table.lpszDesp << " " << table.lpszAlias;

		for (int j = 0; j < table.nFieldNum; j++) {
			const tagMemDBField &field = table.sFieldArray[j];
			qDebug() << " " << field.lpszName << " " << field.lpszDesp;

		}

		table2tableDict[table.lpszName] = const_cast<tagMemDBTable *>(&table);
	}
	qDebug() << "ÄãºÃ" << "!";


	QHashIterator<QString, CardInfo> it(cardType2Card);
	while (it.hasNext()) {
		it.next();
		CardInfo *c = const_cast<CardInfo *>(&it.value());
		if (!table2tableDict.contains(c->tableName)) {
			qDebug() << "Fail to find card table :" << c->name;
			exit(0);
		}

		c->alias = table2tableDict[c->tableName]->lpszDesp;

	}

	{
	QHashIterator<QString, CardInfo> it(cardType2Card);
	while (it.hasNext()) {
		it.next();
		CardInfo *c = const_cast<CardInfo *>(&it.value());


		
		qDebug() << c->name << " " << c->alias;
	}
}
	std::cout << "hello!" << std::endl;
	return 0;
}