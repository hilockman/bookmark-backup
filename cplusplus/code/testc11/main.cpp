#include <iostream>
#include <string>
void TrimEnd(char* lpszTrim)
{
	register int	i;
	for (i = (int)strlen(lpszTrim); i >= 0; i--)
	{
		if (lpszTrim[i] == '\0')
			continue;

		if (lpszTrim[i] == '\n' || lpszTrim[i] == '\r' || lpszTrim[i] == '\r\n' || lpszTrim[i] == '\n\r')
			lpszTrim[i] = '\0';
		else
			break;
	}
}

int main(char *argv, int argc)
{
	
	std::cout<<"hello"<<std::endl;
	char temp[] = "hello                 ";
	std::cout << "before TrimEnd ="<<temp<<'*'<< std::endl;
	TrimEnd(temp);
	std::cout << "after TrimEnd ="<< temp << '*' << std::endl;
	
	return 0;
}