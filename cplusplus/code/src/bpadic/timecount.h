#ifndef  TIME_COUNT_HEADER______________
#define TIME_COUNT_HEADER______________


#include <ctime>
#include <iostream>

/************************************************************************/
/* ʱ���ʱ�࣬��Ҫ                                                                     */
/************************************************************************/
class TimeCount
{
private:
	time_t start_, end_;
	double duration;//��¼ʱ��
	std::string name;
public:
	TimeCount()
	{
		start_ = clock();
	}
	TimeCount(const char *name)
		:name(name)
	{
		start_ = clock();
	}

	void showTime()
	{
		end_ = clock();
		duration = (double)(end_ - start_) / CLOCKS_PER_SEC;
		
		std::cout <<name<<(name.empty()?"" : " ")<< "spend time " << duration << " seconds" << std::endl;
		
	}
};




#define TC_S(name) { TimeCount t(##name);

#define TC_E()  t.showTime(); }


#endif