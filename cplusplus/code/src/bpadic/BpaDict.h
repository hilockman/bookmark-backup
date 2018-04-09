#pragma	once

#include	"BpaMemDBDefine.h"

struct	_Bpa_Dict_
{
	const char*	szTable;			//	BPA�����������õı�
	const char*	szCardKey;			//	BPA�����ݿ����
	const char*	szFieldName;		//	BPA���ݷ����ֶ�
	short	nFieldStart;
	short	nFieldEnd;
	//char*	szFieldFormat;
	short	nFieldType;
	short	nFieldLen;
	short	nFieldDecimal;
	double	fFieldNomValue;
	double	fFieldMinValue;
	double	fFieldMaxValue;
	unsigned char	bFieldCategory;				//	���������ȶ����ݣ������Ǵӳ����ļ��ж�ȡ���Ǵ��ȶ��ļ��ж�ȡ
	unsigned char	bFieldModabled;				//	�ֶο��޸ı��
	//char*	szFieldComment;
}	DISALIGN;
typedef	struct	_Bpa_Dict_	tagBpa_Dict;

typedef	struct	_BpaDictIndex_
{
	unsigned char	bCategory;
	const char			szKey[MDB_CHARLEN_SHORTER];
	const char			szTable[MDB_CHARLEN_SHORT];
	std::vector<tagBpa_Dict>	dkFieldArray;
}	tagBpaDictIndex;

struct	_Bpa_Key2I_
{
	const char*	szKey;
	short	nInteger;
}	DISALIGN;
typedef	struct	_Bpa_Key2I_	tagBpaKey2I;
