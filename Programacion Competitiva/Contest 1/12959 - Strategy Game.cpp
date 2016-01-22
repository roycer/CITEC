#include <bits/stdc++.h>

using namespace std;

int main(){
	int jug,rond;
	while(cin>>jug>>rond){
		map<int,int> m;
		for(int i=0;i<rond;i++){
			for(int j=0;j<jug;j++){
				int p;
				cin>>p;
				m[j]+=p;
			}
		}
		map<int,int>::iterator i = m.begin();
		int max=0;
		int jugador=0;
		for(;i!=m.end();i++){
			if(max<=i->second){
				max=i->second;
				jugador=i->first;
			}
		}
		cout<<jugador+1<<endl;
	}
	return 0;
}
