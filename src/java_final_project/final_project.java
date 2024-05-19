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
		if(this.card>10)this.num+=10;
		else this.num+=this.card;
		if(this.card==1) {
			this.num+=11;
			if(this.num>21)this.num-=10;
		}
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
		if(this.num<=21 && this.num>n) {
			this.score+=2;
		}
		else if(this.num<=21 && this.num==n) {
			this.score+=1;
		}
		else if(this.num<=21 && n>21) {
			this.score+=2;
		}
		else this.score-=2;
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
		//建立玩家物件 numplayer.get(0)為莊家
		for(int i=0;i<=players;i++) {
			numplayer.add(new player());
		}
		int count0=1,count1=3;
		do {
			//莊家抽牌
			System.out.print("\r\n莊家第1張牌:");
			if(!drawcard(numplayer,poker,0)) {
				break;
			}
			System.out.print("莊家第2張牌：");
			if(!drawcard(numplayer,poker,0)) {
				break;
			}
			int n=3;
			while(numplayer.get(0).getnum()<=15){
				System.out.print("莊家第"+(n++)+"張牌：");
				if(!drawcard(numplayer,poker,0)) {
					break;
				}
			}
			System.out.println();
			System.out.println("莊家點數:"+numplayer.get(0).getnum());
			System.out.println();
			//玩家抽牌
			count1=1;
			System.out.println("玩家第"+(count0)+"輪第"+(count1++)+"次抽卡");
			if(!drawcard(numplayer,poker)) {
				break;
			}
			System.out.println("\r\n玩家第"+(count0)+"輪第"+(count1++)+"次抽卡");
			if(!drawcard(numplayer,poker)) {
				break;
			}
			
			for(int i=1;i<numplayer.size();i++) {
				do {
					if(numplayer.get(i).getnum()>21) {
						System.out.println("玩家"+i+"爆掉了!");
						break;
					}
					System.out.print("\r\n玩家"+i+"目前點數為"+numplayer.get(i).getnum());
					System.out.println("  莊家為:"+numplayer.get(0).getnum());
					System.out.println("是否繼續抽牌(輸入t抽牌，輸入f不抽牌");
					input=sc.next();
					if(input.equalsIgnoreCase("t")) {
						System.out.print("此張牌為：");
						if(!drawcard(numplayer,poker,i)) {
							break;
						}
					}
					else if(input.equalsIgnoreCase("f")) {
						System.out.println("已跳過");			
					}
					else System.out.println("輸入錯誤，請重新輸入");
				}while(!input.equalsIgnoreCase("f"));
				numplayer.get(i).scorecal(numplayer.get(0).getnum());
				System.out.println("\r\n玩家"+i+"目前分數為："+numplayer.get(i).getscore());
			}
			for(int i=0;i<numplayer.size();i++) {
				numplayer.get(i).numreturn();
			}
			count0++;
		}while(!(poker[0].isEmpty() && poker[1].isEmpty() && poker[2].isEmpty() && poker[3].isEmpty()));
		System.out.println("沒牌了~遊戲結束，玩家分數如下：");
		for(int i=1;i<numplayer.size();i++) {
			System.out.println("玩家"+i+":"+numplayer.get(i).getscore());
		}
		
		
		
		
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
	public static boolean drawcard(List<player> numplayer,ArrayList<Integer>[] poker){
		//隨機抽取
		int card,count=0;
		for(int i=1;i<numplayer.size();i++) {
			int randomcolor=(int)(Math.random()*poker.length);
			int randomnum=(int)(Math.random()*poker[randomcolor].size());
			while(poker[randomcolor].isEmpty()) {
				if(count>3)return false;
				randomcolor=count++;
			}
			card=poker[randomcolor].get(randomnum);
			numplayer.get(i).setcard(card);
			numplayer.get(i).setcolor(randomcolor);
			poker[randomcolor].remove(randomnum);
			System.out.print("第"+i+"位玩家：");
			System.out.println(numplayer.get(i).getcolor()+""+numplayer.get(i).getcard());
			numplayer.get(i).numadd();
		}
		return true;
	}
	//抽單張
	public static boolean drawcard(List<player> numplayer,ArrayList<Integer>[] poker,int i) {
		int card,count=0;
		int randomcolor=(int)(Math.random()*poker.length);
		int randomnum=(int)(Math.random()*poker[randomcolor].size());
		while(poker[randomcolor].isEmpty()) {
			randomcolor=count++;
			if(count>4)return false;
		}
		card=poker[randomcolor].get(randomnum);
		poker[randomcolor].remove(randomnum);
		numplayer.get(i).setcard(card);
		numplayer.get(i).setcolor(randomcolor);
		System.out.println(numplayer.get(i).getcolor()+""+numplayer.get(i).getcard());
		numplayer.get(i).numadd();
		return true;
	}
}
