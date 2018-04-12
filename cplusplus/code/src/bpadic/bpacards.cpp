#include "BpaCards.h"
#include <QFile>
#include <QJsonDocument>
#include <QJsonArray>
#include <QJsonObject>


BpaCards::BpaCards(const std::vector<CardInfo *> &cards)
:cards(cards)
{
}


BpaCards::~BpaCards()
{
}


bool BpaCards::save()
{
	QFile saveFile("bpacards.json");
	if (!saveFile.open(QIODevice::WriteOnly)) {
		qWarning("Couldn't open save file.");
		return false;
	}

	QJsonArray bpaObject;
	write(bpaObject);
	QJsonDocument saveDoc(bpaObject);
	saveFile.write(saveDoc.toJson());
	return true;
}
void write(QJsonObject &json, tagBpa_Dict *field, tagMemDBField *dbField) {
	json["name"] = field->szFieldName;
	if (dbField) {
		json["alias"] = dbField->lpszDesp;
		QString type = "unknown";
		switch (dbField->nDataType) {
		case MDB_BIT:	//	8位整数
			type = "int(8)";
			break;

		case	MDB_SHORT:	//	16位整数
			type = "int(16)";
			break;
		case MDB_INT :	//	32位整数
			type = "int(32)";
			break;
		case	MDB_FLOAT:	//	32位实数
			type = "float";
			break;
		case MDB_DOUBLE:	//	64位实数
			type = "double";
			break;
		case  MDB_STRING:	//	字符串
			type = "string";
			break;
		case MDB_CHAR:	//	字符
			type = "string";
			break;
		case MDB_RESTRICT:
		default:
			break;

		}
		
		json["dataType"] = type;
	}
		
}
void write(QJsonObject &json, CardInfo *info) {
	
	json["name"] = info->name;
	json["alias"] = info->alias;
	json["type"] = (info->type == DatCard ? "dat" : "swi");
	json["table"] = info->tableName;

	QJsonArray bpaObject;
	for (int i = 0; i < info->fields.size(); i++) {
		QJsonObject fieldObject;
		write(fieldObject, info->fields.at(i), info->dbFields.at(i));
		bpaObject.append(fieldObject);
	}
	json["field"] = bpaObject;
}

void BpaCards::write(QJsonArray &json) const
{

	for (int i = 0; i < cards.size(); i++) {
		QJsonObject cardObject;
		::write(cardObject, cards.at(i));
		json.append(cardObject);
	}

}