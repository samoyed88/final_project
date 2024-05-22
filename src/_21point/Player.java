package _21point;

class player{
	private int num,score,color,card;
	player(){
		this.num=0;
		this.score=10;
		this.color=0;
		this.card=0;
	}
	//點數累加(單局)  **特例:A為1or11
	void numadd(int n){
		this.num+=n;
	}
	//點數歸零
	void numreturn() {
		this.num=0;
	}
	//主程式獲取點數
	int getnum() {
		return this.num;
	}
	//分數計算(傳入莊家點數)
	void scorecal(int n) {
		if(num<=21 && num>n) {
			System.out.println("贏了莊家!");
			this.score+=2;
		}
		else if(num<=21 && num==n) {
			System.out.println("跟莊家平手");
			this.score+=1;
		}
		else if(num<=21 && n>21) {
			System.out.println("贏了莊家!");
			this.score+=2;
		}
		else {
			System.out.println("輸了莊家!");
			this.score-=2;
		}
	}
	//主程式獲取分數
	int getscore() {
		return this.score;
	}
	//卡牌花色
	void setcolor(int c) {
		this.color=c;
	}
	//獲取花色
	String getcolor() {
		switch(this.color) {
		case 0:
			return "C";
		case 1:
			return "D";
		case 2:
			return "H";
		case 3:
			return "S";
		}
		return "Error";
	}
	void setcard(int c) {
		this.card=c;
	}
	int getcard() {
		return this.card;
	}
}
