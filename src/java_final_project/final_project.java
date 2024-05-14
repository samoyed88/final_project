package java_final_project;
import java.util.*;

class player{
	private int num,score,color,card;
	player(){
		this.num=0;
		this.score=10;
		this.color=0;
		this.card=0;
	}
	//點數累加(單局)  **特例:A為1or11
	void numadd(){
		if(this.num>10)this.num+=10;
		else this.num+=this.card;
	}
	//點數歸零
	void numreturn() {
		this.num=0;
	}
	//主程式獲取點數
	int getnum() {
		return this.num;
	}
	//分數增加
	void scoreadd(int n) {
		this.score+=n;
	}
	//主程式獲取分數
	int scoreget() {
		return this.score;
	}
	//卡牌花色
	void coloradd(int c) {
		this.color=c;
	}
	//獲取花色
	String getcolor() {
		switch(this.color) {
		case 1:
			return "C";
		case 2:
			return "D";
		case 3:
			return "H";
		case 4:
			return "S";
		}
		return "Error";
	}
	void cardadd(int c) {
		this.card=c;
	}
	int getcard() {
		return this.card;
	}
	
}

public class final_project {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		//使用arrayList建立一副牌
		ArrayList<Integer>[] poker=new ArrayList[4];
		for(int i=0;i<4;i++) {
			poker[i]=new ArrayList<Integer>();
		}
		creatpoker(poker);
		String input;
		int players;
		List<player> numplayer = new ArrayList<>();
		//開始遊戲
		do {
			System.out.println("21點遊戲，請輸入start開始遊戲");
			input=sc.next().toLowerCase();
		}while(!(input.equalsIgnoreCase("start")));
		//遊玩人數設定
		System.out.println("請輸入遊玩人數(1-4)");
		input=sc.next();
		while(!(input.matches("[1-4]"))) {
			System.out.println("輸入錯誤請重新輸入");
			System.out.println("請輸入遊玩人數");
			input=sc.next(); 
		}
		players=Integer.parseInt(input);
		if(players<4) {players--;numplayer.add(new player());} // 添加 player1 (player1為莊家}
		//建立玩家物件
		for(int i=0;i<players;i++) {
			numplayer.add(new player());
		}
		//第一把遊戲
		System.out.println("第一輪點數");
		drawcard(numplayer,poker);
		for(int i=0;i<numplayer.size();i++) {
			System.out.println(numplayer.get(i).getnum());
		}
		System.out.println("第二輪點數");
		drawcard(numplayer,poker);
		for(int i=0;i<numplayer.size();i++) {
			System.out.println(numplayer.get(i).getnum());
		}
		int t=0,count=3;
		do{
			System.out.println("第"+(count++)+"輪點數");
			for(int i=0;i<numplayer.size();i++) {
				System.out.println("玩家"+(i+1)+"目前點數為"+numplayer.get(i).getnum());
				System.out.println("是否繼續抽牌(輸入t抽牌，輸入f不抽牌");
					do {
						input=sc.next();
						if(input.equalsIgnoreCase("t")) {
							numplayer.get(i).numadd(drawcard(poker));
							System.out.println("已抽牌");
							break;
						}
						else if(input.equalsIgnoreCase("f")) {
							System.out.println("已跳過");
							t++;
							break;
						}
						System.out.println("輸入錯誤，請重新輸入");
					}while(true);
			}
		}while(!(t>numplayer.size()));
				
		sc.close();
	}
	//poker[i]陣列的i=0為梅花 i=1為菱形 i=2為紅心 i=3為黑桃 各有1~13
	public static void creatpoker(ArrayList<Integer>[] poker){
		int n=1;
		for(int i=0;i<13;i++) {
			poker[0].add(n);
			poker[1].add(n);
			poker[2].add(n);
			poker[3].add(n);
			n++;
		}
	}
	//發牌一輪
	public static void drawcard(List<player> numplayer,ArrayList<Integer>[] poker){
		//隨機抽取
		int card;
		for(int i=0;i<numplayer.size();i++) {
			int randomcolor=(int)(Math.random()*poker.length);
			int randomnum=(int)(Math.random()*poker[randomcolor].size());
		card=poker[randomcolor].get(randomnum);
		numplayer.get(i).numadd(card);
		poker[randomcolor].remove(randomnum);
		}
	}
	//抽單張
	public static int drawcard(ArrayList<Integer>[] poker) {
		int card;
		int randomcolor=(int)(Math.random()*poker.length);
		int randomnum=(int)(Math.random()*poker[randomcolor].size());
		card=poker[randomcolor].get(randomnum);
		poker[randomcolor].remove(randomnum);
		return card;
	}
}
