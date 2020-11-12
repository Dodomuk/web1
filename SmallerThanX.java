package e_for;

import java.util.Scanner;

public class SmallerThanX {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int x = sc.nextInt();
		int[] nArr = new int[n];
		for (int i = 0; i < n; i++) {
			
			nArr[i] = sc.nextInt();
			
		}
		
		for (int j = 0; j < n; j++) {
			
			if(x > nArr[j]) {
			System.out.print(nArr[j]);
			System.out.print(" ");
			}
		}

	}

}
