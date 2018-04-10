#ifndef  TIME_COUNT_HEADER______________
#define TIME_COUNT_HEADER______________


#include <ctime>
#include <iostream>

/************************************************************************/
/* 时间计时类，需要                                                                     */
/************************************************************************/
class TimeCount
{
private:
	time_t start_, end_;
	double duration;//记录时间
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