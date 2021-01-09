package j_factorial;

import java.util.Scanner;

public class Hanoi {

	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		sb.append((int)(Math.pow(2, n)) - 1);
		sb.append('\n');
		
		hanoi(n,1,2,3);
		System.out.println(sb);
	}

	public static void hanoi(int n, int from, int by, int to) {
	
		  if(n==1) {
			  sb.append(from + " " + to + "\n");
			  return;
		  }else {
			  hanoi(n-1,from,to,by);
			  sb.append(from + " " + to + "\n");
			  hanoi(n-1,by,from,to);
		  }
	}

}
