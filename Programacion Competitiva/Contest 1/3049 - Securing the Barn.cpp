#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <set>
#include <algorithm>

using namespace std;


int contarBit(int x){
	int cont=0;
	while(x){
		x &= x-1;
		cont++;
	}
	return cont;
}

bool vocal(char c){
	return c=='a' || c=='e' || c=='i' || c=='o' || c=='u';
}

int main(){
	string str,cha; 
	set<string> resp;
	int L,C,cont_v;
	int cont_nv;
	char d;

	cin>>C>>L;
	
	for(int i=0;i<L;++i){
		cin>>d;	
		cha.push_back(d);
	}
	sort(cha.begin(),cha.end());
	
	for(int i=0;(i<1<<L); ++i){
		if(contarBit(i)==C){
			cont_v = 0;
			cont_nv = 0;
			str.clear();
			for(int j=0;j<L; ++j){
				if(i & (1<<j)) {
					str.push_back(cha[j]);
					if(vocal(cha[j]))
						cont_v++;
					else
						cont_nv++;					
				}
			}
			if(cont_v>=1 && cont_nv >=2)
				resp.insert(str);
		}
	}

	set<string>::iterator it=resp.begin();

	for(; it!=resp.end();++it){
		cout<<*it<<endl;
	}
	return 0;
}
