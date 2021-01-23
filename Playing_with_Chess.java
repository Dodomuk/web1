package k_bruteForce;

import java.util.Scanner;

public class Playing_with_Chess {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		String[][] tile = new String[n][m];
		int res = 0;
		for (int i = 0; i < n; i++) { //타일을 입력 받음
			String input = sc.next();
			  tile[i] = input.split("");
	
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(tile[i][j] == tile[i-1][j]) {
					
					res++;
					
				}
			}
		}
		System.out.println(res);
		
	}
	
}
