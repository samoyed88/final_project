package java_final_project;
import java.util.*;

class player{
	private int num,score;
	player(){
		this.num=0;
		this.score=10;
	}
	void numadd(int n){
		this.num+=n;
	}
	void numreturn() {
		this.num=0;
	}
	int getnum() {
		return this.num;
	}
	void scoreadd(int n) {
		this.score+=n;
	}
	int scoreget() {
		return this.score;
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
		
				
		sc.close();
	}
	public static void creatpoker(ArrayList<Integer>[] poker){
		int n=1;
		for(int i=0;i<13;i++) {
			poker[0].add(n);
			poker[1].add(n);
			poker[2].add(n);
			poker[3].add(n);
			if(n<10)n++;
			else continue;
		}
	}

}
