#include <iostream>
#include <map>
#include <string>
#include <vector>
/**
c++ 11 Features
https://www.codeproject.com/articles/570638/ten-cplusplus-features-every-cplusplus-developer
*/
using namespace std;


/**
Range-based for loops
C++11 augmented the for statement to support the "foreach" paradigm of iterating over collections. In the new form, it is possible to iterate over C-like arrays, initializer lists and anything for which the non-member begin() and end() functions are overloaded.
*/
void testFor() {
	std::map<std::string, std::vector<int>> map;
	std::vector<int> v;
	v.push_back(1);
	v.push_back(2);
	v.push_back(3);
	map["one"] = v;

	for(const auto& kvp : map) 
	{
	  std::cout << kvp.first << std::endl;

	  for(auto v : kvp.second)
	  {
		 std::cout << v << std::endl;
	  }
	}

	int arr[] = {1,2,3,4,5};
	for(int& e : arr) 
	{
	  e = e*e;
	}

}

/**
Override and final
*/
class B 
{
public:
   virtual void f(short) {std::cout << "B::f" << std::endl;}
};

class D : public B
{
public:
   virtual void f(short) override {std::cout << "D::f" << std::endl;}
};

/**
Smart pointers
*/
#include <memory>
void foo(int* p)
{
   std::cout <<"pointer p = "<< *p << std::endl;
}

void testSmartPorinter() {
	std::unique_ptr<int> p1(new int(42));
	std::unique_ptr<int> p2 = std::move(p1); // transfer ownership

	if(p1)
	  foo(p1.get());
    else {
		std::cout <<"p1 = nullptr" << std::endl;
	}

	(*p2)++;

	if(p2)
	  foo(p2.get());
}

/**
Lambdas
*/
#include <algorithm>
#include <functional>
void testLambdas() {
	std::vector<int> v;
	v.push_back(1);
	v.push_back(2);
	v.push_back(3);

	std::for_each(std::begin(v), std::end(v), [](int n) {std::cout << "e = "<<n << std::endl;});

	auto is_odd = [](int n) {return n%2==1;};
	auto pos = std::find_if(std::begin(v), std::end(v), is_odd);
	if(pos != std::end(v))
		std::cout <<"ood is "<< *pos << std::endl;
	
	
	std::function<int(int)> lfib = [&lfib](int n) {return n < 2 ? 1 : lfib(n-1) + lfib(n-2);};
	
	for (int i = 1; i < 10 ; i++) {
		cout<<"fib("<<i<<") = "<<lfib(i)<<endl;
	}
}

/**
non-member begin() and end()
*/

void testBeginAndEnd() {
	int arr[] = {1,2,3};
	std::for_each(std::begin(arr), std::end(arr), [](int n) {std::cout<<"e =" << n << std::endl;});

	auto is_odd = [](int n) {return n%2==1;};
	auto pos = std::find_if(std::begin(arr), std::end(arr), is_odd);
	if(pos != std::end(arr))
	  std::cout <<"odd is "<< *pos << std::endl;
}
template <typename Iterator>
void bar(Iterator begin, Iterator end) 
{
   std::for_each(begin, end, [](int n) {std::cout << n << std::endl;});

   auto is_odd = [](int n) {return n%2==1;};
   auto pos = std::find_if(begin, end, is_odd);
   if(pos != end)
      std::cout <<"odd is "<< *pos << std::endl;
}

template <typename C>
void foo1(C c)
{
   bar(std::begin(c), std::end(c));
}

template <typename T, size_t N>
void foo1(T(&arr)[N])
{
   bar(std::begin(arr), std::end(arr));
}

void testBeginAndEndEx() {
	
	int arr[] = {1,2,3};
	foo1(arr);

	std::vector<int> v;
	v.push_back(1);
	v.push_back(2);
	v.push_back(3);
	foo1(v);
}
/**
static_assert and type traits
*/
#include <assert.h>
//#include <type_traite>
template <typename T, size_t Size>
class Vector
{
   static_assert(Size > 3, "Size is too small");
   T _points[Size];
};

int testAsset()
{
   Vector<int, 16> a1;
  // Vector<double, 2> a2;
   return 0;
}
/**
Move semantics
*/
template <typename T>
class Buffer 
{
   std::string          _name;
   size_t               _size;
   std::unique_ptr<T[]> _buffer;

public:
   // default constructor
   Buffer():
      _size(16),
      _buffer(new T[16])
   {cout<<"call default constructor"<<endl;}

   // constructor
   Buffer(const std::string& name, size_t size):
      _name(name),
      _size(size),
      _buffer(new T[size])
   {
	    cout<<"call constructor"<<endl;
	   
   }

   // copy constructor
   Buffer(const Buffer& copy):
      _name(copy._name),
      _size(copy._size),
      _buffer(new T[copy._size])
   {
	   cout<<"call copy constructor"<<endl;
      T* source = copy._buffer.get();
      T* dest = _buffer.get();
      std::copy(source, source + copy._size, dest);
   }

   // copy assignment operator
   Buffer& operator=(const Buffer& copy)
   {
	   cout<<"call copy assignment operator"<<endl;
      if(this != &copy)
      {
         _name = copy._name;

         if(_size != copy._size)
         {
            _buffer = nullptr;
            _size = copy._size;
            _buffer = _size > 0 > new T[_size] : nullptr;
         }

         T* source = copy._buffer.get();
         T* dest = _buffer.get();
         std::copy(source, source + copy._size, dest);
      }

      return *this;
   }

   // move constructor
   Buffer(Buffer&& temp):
      _name(std::move(temp._name)),
      _size(temp._size),
      _buffer(std::move(temp._buffer))
   {
	   cout<<"call move constructor"<<endl;
      temp._buffer = nullptr;
      temp._size = 0;
   }

   // move assignment operator
   Buffer& operator=(Buffer&& temp)
   {
	   cout<<"call move assignment operator"<<endl;
      assert(this != &temp); // assert if this is not a temporary

      _buffer = nullptr;
      _size = temp._size;
      _buffer = std::move(temp._buffer);

      _name = std::move(temp._name);

      temp._buffer = nullptr;
      temp._size = 0;
      
      return *this;
   }
};

template <typename T>
Buffer<T> getBuffer(const std::string& name) 
{
   Buffer<T> b(name, 128);
   return b;
}
int testMove()
{
	cout<<"Buffer<int> b1"<<endl;
   Buffer<int> b1;
   
   cout<<"Buffer<int> b2('buf2', 64)"<<endl;
   Buffer<int> b2("buf2", 64);
   
    cout<<"Buffer<int> b3 = b2"<<endl;
   Buffer<int> b3 = b2;
   
   cout<<"Buffer<int> b4 = getBuffer<int>('buf4')"<<endl;
   Buffer<int> b4 = getBuffer<int>("buf4");
   
    cout<<"b1 = getBuffer<int>('buf5')"<<endl;
   b1 = getBuffer<int>("buf5");
   return 0;
}

int main(int argc, char **argv) {
	cout<<"*********test for"<<endl;
	testFor();
	cout<<"*********end test testFor"<<endl;
	
	cout<<"*********test testSmartPorinter"<<endl;
	testSmartPorinter();
	cout<<"*********end test testSmartPorinter"<<endl;
	
	cout<<"*********test testLambdas"<<endl;
	testLambdas();
	cout<<"*********end test testLambdas"<<endl;
	
	cout<<"*********test testBeginAndEnd"<<endl;
	testBeginAndEnd();
	cout<<"*********end test testBeginAndEnd"<<endl;
	
	cout<<"*********test testBeginAndEndEx"<<endl;
	testBeginAndEndEx();
	cout<<"*********end test testBeginAndEndEx"<<endl;	

	cout<<"*********test testMove"<<endl;
	testMove();
	cout<<"*********end test testMove"<<endl;	
	
	D d;
	B *b = &d;
	b->f(0);
	cout<<"hello"<<endl;
	return 0;
}