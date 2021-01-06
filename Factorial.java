package j_factorial;

import java.util.Scanner;

public class Factorial {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int res = 1;
		if(N != 0) {
		for (int i = 1; i < N+1; i++) {
			res = res*i;
		}
		}else {
			res=1;
		}
		System.out.println(res);
	}

}
