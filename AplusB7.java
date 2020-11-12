package e_for;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AplusB7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		try {

			int n = Integer.parseInt(br.readLine());
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				sb.append("case #" + (i + 1) + ": "
						+ (Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken())));
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
