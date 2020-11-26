package g_string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Repeat_String {

	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		try {
			// T : 테스트 케이스의 개수
			int T = Integer.parseInt(br.readLine());
			int R;
			char[] chars;

			for (int i = 0; i < T; i++) {
				st = new StringTokenizer(br.readLine());
				// R : 문자반복횟수
				R = Integer.parseInt(st.nextToken());

				chars = st.nextToken().toCharArray();

				for (int j = 0; j < chars.length; j++) {
					for (int j2 = 0; j2 < R; j2++) {
						sb.append(chars[j]);
					}

				}
				sb.append("\n");
			}

			System.out.println(sb);
			br.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

	}

}
