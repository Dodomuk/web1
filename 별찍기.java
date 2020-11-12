package e_for;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class º°Âï±â {

	public static void main(String[] args) {
		
		BufferedReader br 
		= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		try {
			int n = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < n; i++) {	
				if(i > 0) {
					sb.append("\n");
				}
				for (int j = 0; j < n-(i+1); j++) {
					sb.append(" ");
					
				}
				for (int k = 0; k < i+1; k++) {
					
					sb.append("*");
					
				}
			}

			System.out.println(sb);
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}

}
