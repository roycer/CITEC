#include <bits/stdc++.h>

using namespace std;

int t,n,m,cont;
char d,c[21][21];
bool band,band2;

int dx[8]={1,-1,0,0,-1,-1,1,1};
int dy[8]={0,0,1,-1,1,-1,1,-1};

bool valid(int i, int j){
	return i>=0 && j>=0 && i<n && j<m;
}

int main(){
	
	cin>>t;
	while(t--){
		band=true;
		band2=false;
		cin>>n>>m;	
				
		for(int i=0;i<n;++i){
			for(int j=0;j<m;++j){
				cin>>d;
				c[i][j]=d;
			}
		}
		int nx,ny;
		for(int i=0;i<n;++i){
			for(int j=0;j<m;++j){
				if(c[i][j]!='F'){
					band2=true;
					cont=0;
					for(int k=0;k<8;++k){
						nx = dx[k]+i;
						ny = dy[k]+j;
						if(valid(nx,ny) && (c[nx][ny]=='F'))
							cont++;
					}
					if(cont != (c[i][j]-'0')){
						band=false;
						i=n;
						j=m;
						break;
					}
				}
			}
		}
		
		if(band && band2){
			cout<<"Well done Clark!"<<endl;
		}
		else
			cout<<"Please sweep the mine again!"<<endl;
			
	}	
}
