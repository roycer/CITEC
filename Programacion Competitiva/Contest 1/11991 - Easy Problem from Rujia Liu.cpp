#include <bits/stdc++.h>

using namespace std;

int main(){
	int a,b,temp,temp2;
	map<int, vector<int> >qy;

	while(cin>>a>>b){
		int pos[a];
		for(int i=0;i<a;++i){
			cin>>temp;
			qy[temp].push_back(i);
		}

		for(int i=0;i<b;++i){
			cin>>temp>>temp2;
			
			if(qy[temp2].size()>=temp)
				cout<<qy[temp2][temp-1]+1<<endl;
			else
				cout<<0<<endl;	
		}
	}
	
}
