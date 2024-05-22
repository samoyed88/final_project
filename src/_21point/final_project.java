package _21point;
import java.util.*;


public class final_project {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		//使用arrayList建立一副牌
		ArrayList<String> poker=new ArrayList<String>();
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
		int round=1;//回合數
		int count=1;//次數
		for(int i=0;i<52;i++) {
			System.out.println(drawcard(poker)+" ");
		}
		
		outer:do {
			//第n輪第一次抽牌
			for(int j=0;j<2;j++) {
				count=1;
				System.out.println("第"+(round)+"輪第"+(count++)+"次抽牌");
				for(int i=1;i<numplayer.size();i++) {
					System.out.print("第"+i+"位玩家");
					int num=drawcard(poker);
					if(num==0)break outer;
					else if(num==1) {
						System.out.print("目前點數："+numplayer.get(i).getnum()+"\r\n玩家"+i);
						numplayer.get(i).numadd(one());
					}
					else numplayer.get(i).numadd(num);
				}
			}
			//第n輪第3+次抽牌
			for(int i=1;i<numplayer.size();i++) {
				do {
					if(numplayer.get(i).getnum()>21) {
						System.out.println("玩家"+i+"爆掉了!");
						break;
					}
					//各玩家是否繼續抽牌
					System.out.print("\r\n玩家"+i+"目前點數為"+numplayer.get(i).getnum());
					System.out.println("是否繼續抽牌(輸入t抽牌，輸入f不抽牌");
					input=sc.next();
					if(input.equalsIgnoreCase("t")) {
						int num=drawcard(poker);
						if(num==0)break outer;
						else if(num==1) {
							System.out.print("目前點數："+numplayer.get(i).getnum()+"\r\n玩家"+i);
							numplayer.get(i).numadd(one());
						}
						else numplayer.get(i).numadd(num);
					}
					else if(input.equalsIgnoreCase("f")) {
						System.out.println("結束抽牌");
					}
					else System.out.println("輸入錯誤，請重新輸入");
				}while(!input.equalsIgnoreCase("f"));
			}
			//莊家抽牌
			System.out.println();
			System.out.println("莊家");
			int num=drawcard(poker);
			if(num==0)break outer;
			else numplayer.get(0).numadd(num);
			num=drawcard(poker);
			if(num==0)break outer;
			else numplayer.get(0).numadd(num);
			if(numplayer.get(0).getnum()<15) {
				num=drawcard(poker);
				if(num==0)break outer;
				else numplayer.get(0).numadd(num);
			}
			System.out.println("\r\n莊家點數："+numplayer.get(0).getnum());
			grade(numplayer);
			round++;
		}while(!poker.isEmpty());
		System.out.println();
		finalscore(numplayer);
		sc.close();
	}
	//poker[i]陣列的i=0為梅花 i=1為菱形 i=2為紅心 i=3為黑桃 各有1~13
	public static void creatpoker(ArrayList<String> poker){
		for(int i=1;i<=10;i++) {
			poker.add("C-"+i);
		}
		poker.add("C-J");
		poker.add("C-Q");
		poker.add("C-K");
		for(int i=1;i<=10;i++) {
			poker.add("D-"+i);
		}
		poker.add("D-J");
		poker.add("D-Q");
		poker.add("D-K");
		for(int i=1;i<=10;i++) {
			poker.add("H-"+i);
		}
		poker.add("H-J");
		poker.add("H-Q");
		poker.add("H-K");
		for(int i=1;i<=10;i++) {
			poker.add("S-"+i);
		}
		poker.add("S-J");
		poker.add("S-Q");
		poker.add("S-K");
	}
	
	//抽單張
	public static int drawcard(ArrayList<String> poker) {
		if(poker.isEmpty())return 0;
		int random=(int)(Math.random()*poker.size());
		String card=poker.get(random);
		System.out.println("抽到:"+card);
		int num=0;
		if(card.contains("10")) {
			num=10;
		}
		else if(card.charAt(2)=='J') {
			num=10;
		}
		else if(card.charAt(2)=='Q') {
			num=10;
		}
		else if(card.charAt(2)=='K') {
			num=10;
		}
		else num=Integer.parseInt(String.valueOf(card.charAt(2)));
		poker.remove(random);
		return num;
	}
	
	//回合結束後分數計算
	public static void grade(List<player> numplayer) {
		for(int i=1;i<numplayer.size();i++) {
			System.out.print("玩家"+i);
			numplayer.get(i).scorecal(numplayer.get(0).getnum());
			System.out.println("玩家"+i+"的分數:"+numplayer.get(i).getscore());
			numplayer.get(i).numreturn();//玩家點數重置
		}
		System.out.println();
		numplayer.get(0).numreturn(); //莊家點數重置
	}
	
	//點數1額外處理
	public static int one() {
		Scanner sc=new Scanner(System.in);
		String input;
		int num=0;
		do {
			System.out.println("抽到1，選擇1請輸入a，選擇11請輸入b");
			input=sc.next();
			if(input.equalsIgnoreCase("a")) {
				num=1;
			}
			else if(input.equalsIgnoreCase("b")) {
				num=11;
			}
			else System.out.println("輸入錯誤，請重新輸入");
		}while(!input.equalsIgnoreCase("a")&&!input.equalsIgnoreCase("b"));
		return num;
	}
	
	//結算分數
	public static void finalscore(List<player> numplayer) {
		int score=0,who=0;
		for(int i=1;i<numplayer.size();i++) {
			if(numplayer.get(i).getscore()>score) {
				score=numplayer.get(i).getscore();
				who=i;
			}
		}
		System.out.println("玩家"+who+"是贏家，分數"+score+"分");
	}
}
