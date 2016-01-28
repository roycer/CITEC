#include <bits/stdc++.h>

using namespace std;

int padre[1000];
void ini(int n){
	for(int i=0;i<=n;++i){
		padre[i] = i;
	}
}
int find(int x){
	
	if(padre[x]==x)
		return x;

	return padre[x] = find(padre[x]);
}

void Union(int x, int y){
	x = find(x);
	y = find(y);
	
	padre[x] = y;
}

int main(){
	
	int t;
	int n;
	char c;
	int comp1, comp2;
	string s;
	
	int contS;
	int contU;
	bool b=false;
	scanf("%d",&t);
	while(t--){
		contS=0;
		contU=0;
		scanf("\n%d\n",&n);
		ini(n);

		while(getline(cin,s) && !s.empty()){
			sscanf(s.c_str(),"%c %d %d",&c,&comp1,&comp2);
			if(c=='c'){
				Union(comp1,comp2);
			}
			else if(c=='q'){
				(find(comp1)==find(comp2))? contS++:contU++;
			}
				
		}
		if(b) printf("\n");
		printf("%d,%d\n",contS,contU);
		b=true;	
	}
}
