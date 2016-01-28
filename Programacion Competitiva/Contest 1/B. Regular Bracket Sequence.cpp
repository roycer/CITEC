#include <iostream>

using namespace std;

int main(){
	int parA =0;
	int res = 0;

	string exp;
	cin>>exp;
	for(int i=0;i<exp.size();++i){
		if(exp[i]=='(') res++;
		else if(exp[i]==')' && res>0){
			parA++;
			res--;
		}
	}
	cout<< parA*2<<endl;
}
