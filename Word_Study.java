package g_string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Word_Study {

	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));

		try {
			
			  char[] word = (br.readLine()).toUpperCase().toCharArray();     
			  int[] wordArr = new int[26];
			  
			for (int i = 0; i < word.length; i++) {
				
				wordArr[(int)word[i]-65]++; 
			}
			
			int max = 0;
			int idx = 0;
			
			for (int j = 0; j < wordArr.length; j++) {
				
				if(max<wordArr[j]) {
					max = wordArr[j];
					idx = j+65; 
				}else if(max == wordArr[j]) {
					idx = 63;
				}

				
			}
			System.out.println((char)idx);			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	}

}
