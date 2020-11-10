package c_arithmetics;

import java.util.Scanner;

public class Multiple {
	
	Scanner sc = new Scanner(System.in);
	
	private int input_1;
	private int input_2;
	
	public static void main(String[] args) {
		
		Multiple mp = new Multiple();
		mp.action();
		
	}
	
	public void action() {
		
		System.out.println("첫번째 세자리 자연수를 입력하세요");
		input_1 = sc.nextInt();
		System.out.println("두번째 세자리 자연수를 입력하세요");
		input_2 = sc.nextInt();
		
		int placeOne = ((input_2 % 100)%10);
		int placeTen = ((input_2 % 100) - placeOne) / 10;
		int placeHundred = (input_2 - placeTen - placeOne) / 100 ;
		
		if(input_1>999 || input_1 < 100 ) {
			return;
		}else if(input_2 > 999 || input_2 < 100 ) {
			return;
		}
		System.out.println("   " + input_1);
		System.out.println("  x" + input_2);
		System.out.println("------");
		System.out.println("  " + (input_1*placeOne));
		System.out.println(" " + (input_1*placeTen));
		System.out.println(input_1*placeHundred);
		System.out.println("------");
		System.out.println(input_1*input_2);

	}

}
