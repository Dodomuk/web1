package g_string;

import java.util.Scanner;

public class FindAlphabet {

	public static void main(String[] args) {
		
		//a~z : 97~122
		
		Scanner sc = new Scanner(System.in);
		
		String input = sc.next();
		char[] inputArr = input.toCharArray();
		int[] alphabetArr = new int[26];
        
		for (int i = 0; i < 26; i++) {
		
			alphabetArr[i] = -1;
			
		}
		
		for (int j = 0; j < inputArr.length; j++) {
			
			for (int k = 0; k < alphabetArr.length; k++) {
				if(inputArr[j] == (char)(97+k)) { //아스키코드 비교
					
					if(alphabetArr[k] == -1) { //중복 방지 (oo두개)
					alphabetArr[k] = j;
					}
				}
			}
		
		}
		for (int l = 0; l < alphabetArr.length; l++) {
			System.out.print(alphabetArr[l] + " ");
		}
	
	}

}
