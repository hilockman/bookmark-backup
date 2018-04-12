#ifndef BPA_CARDS_HEADER____________________
#define BPA_CARDS_HEADER____________________


#include <vector>

enum CardCategory
{
	DatCard = 0,
	SwiCard = 1
};
#include "BpaDictData.h"
#include "BpaMemDBTable.h"
class QJsonArray;

struct CardInfo
{
	const char * name;
	const char * tableName;
	const char * alias;
	CardCategory type;
	std::vector<tagBpa_Dict *> fields;
	std::vector<tagMemDBField* > dbFields;;
};


class BpaCards
{
private:
	const std::vector<CardInfo *> &cards;
public:
	BpaCards(const std::vector<CardInfo *> &cards);
	~BpaCards();

	bool save();

private:
	void write(QJsonArray &json) const;
};

#endif


