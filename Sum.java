package e_for;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int n = Integer.parseInt(br.readLine());
			int sum = 0;

			for (int i = 0; i < n; i++) {

				sum += i+1;

			}
			
			System.out.println(sum);
		
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
