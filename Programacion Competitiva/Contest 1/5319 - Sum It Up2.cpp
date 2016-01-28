#include <bits/stdc++.h>

using namespace std;
int lista[12];
int t,n,e;
bool isNone;
map<string,int> mm;
void imprimir(int mbit){
	bool b=false;
	string s=""; 
	for(int i=n-1;i>=0;--i){
		if(mbit & (1<<i)){
			ostringstream ss;
			if(b) s.push_back('+');
			ss << lista[n-i-1];
			s+=ss.str();
			b=true;
		}
	}

	if(mm[s]==0){
		printf("%s\n",s.c_str());
		mm[s]=1;
	}
}
void bp(int pos, int mbit, int sum){

	if(pos==n || sum==t){
		if(sum==t) {
			imprimir(mbit);
			isNone=false;
		}	
		return;
	}
	
	bp(pos+1,mbit|(1<<n-1-pos),sum+lista[pos]);
	bp(pos+1,mbit,sum);

} 

int main(){
	
	while(scanf("%d %d",&t,&n)!=EOF && n){
	
		printf("Sums of %d:\n",t);
		
		isNone=true;
		mm.clear();

		for(int i=0;i<n;++i){
			scanf("%d",&e);
			lista[i]=e;	
		}

		bp(0,0,0);
		if(isNone) printf("NONE\n");
	}


	return 0;

}
