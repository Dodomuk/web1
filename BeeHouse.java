package i_math_1;

import java.util.Scanner;

public class BeeHouse {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int m=1;
		int res = 0;
		for (int i = 0; i < N; i++) {
			m= m+(6*i);
			
			if(N<=m) {
				
				res = i+1;
				break;
			}
		}
		System.out.println(res);
	}

}
