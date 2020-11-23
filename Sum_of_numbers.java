package g_string;

import java.util.Scanner;

public class Sum_of_numbers {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int sum = 0;
		int N = sc.nextInt();
		int[] nArr = new int[N];
		
			String input = sc.next();
			String[] nString = input.split("");
		 
			for (int i = 0; i<nArr.length; i++) {
				
				nArr[i] = Integer.parseInt(nString[i]); 
				sum += nArr[i];
			}
			System.out.println(sum);
	}
	
}
