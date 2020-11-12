package e_for;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AplusB8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		try {

			int n = Integer.parseInt(br.readLine());
			StringBuilder sb = new StringBuilder();
			int A;
			int B;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				A = Integer.parseInt(st.nextToken());
				B = Integer.parseInt(st.nextToken());
				sb.append("case #" + (i + 1) + ": "
						+ A + " + " + B 
						+ " = " + (A+B));
				sb.append("\n");

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
