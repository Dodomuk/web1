package e_for;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AplusB {

	/*
	 * public static void main(String[] args) { // TODO Auto-generated method stub
	 * 
	 * Scanner sc = new Scanner(System.in);
	 * 
	 * int t = sc.nextInt(); int[] sum = new int[t];
	 * 
	 * for (int i = 0; i < t; i++) {
	 * 
	 * int input_1 = sc.nextInt(); int input_2 = sc.nextInt(); sum[i] = input_1 +
	 * input_2; }sc.close();
	 * 
	 * for (int j : sum) { System.out.println(j); } }
	 */

	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				sb.append(Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()));
				sb.append('\n');
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
