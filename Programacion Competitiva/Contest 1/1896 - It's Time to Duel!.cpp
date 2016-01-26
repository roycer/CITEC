#include <bits/stdc++.h>

using namespace std;


struct card {
	int X;
	int Y;
	int Z;
	card(int X,int Y, int Z){
		this->X = X;
		this->Y = Y;
		this->Z = Z;
	}
};

vector<card> cards;
int A,D,H;
int N;
bool dp(int pos, int x, int y, int z, int c){
	
	if(pos==N) return (c>=2) && (x==A) && (y==D) && (z==H);
	
	if(c>=2 && (x==A) && (y==D) && (z==H))
		return true;

	return dp(pos+1,x,y,z,c) || dp(pos+1,x+cards[pos].X,y+cards[pos].Y,z+cards[pos].Z,c+1);
}


int main(){

	cin>>N;
	cin>>A>>D>>H;
	int n=N;
	while(n--){	
		int X,Y,Z;
		cin>>X>>Y>>Z;
		card temp(X,Y,Z);
		cards.push_back(temp);
	}	
	
	if(dp(0,0,0,0,0))
		cout<<"Y"<<endl;

	else
		cout<<"N"<<endl;
	return 0;
}


