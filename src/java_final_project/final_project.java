package java_final_project;

import java.util.ArrayList;
import java.util.Scanner;

public class final_project {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		//使用arrayList建立一副牌
		ArrayList<Integer>[] poker=new ArrayList[4];
		for(int i=0;i<4;i++) {
			poker[i]=new ArrayList<Integer>();
		}
		creatpoker(poker);
		

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
