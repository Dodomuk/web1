package i_math;

import java.util.Scanner;

public class Rgt_triangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
        boolean flg = true;
		while (true) {

			int[] triangle = { sc.nextInt(), sc.nextInt(), sc.nextInt() };
			
			if (triangle[0] == 0 && triangle[1] == 0 && triangle[2] == 0) {
				break;
			}else if(flg == false){
				sb.append("\r");
			}
			flg = false;
			
			if(triangle[0] != 0 && triangle[1] != 0 && triangle[2] != 0) {
			     if ((Math.pow(triangle[2],2) == Math.pow(triangle[0],2) + Math.pow(triangle[1],2))
					||(Math.pow(triangle[1],2) == Math.pow(triangle[0],2) + Math.pow(triangle[2],2))
					||(Math.pow(triangle[0],2) == Math.pow(triangle[2],2) + Math.pow(triangle[1],2))) {
				sb.append("right");
			} else {
				sb.append("wrong");

			}
			}

		}
		System.out.println(sb);
		sc.close();
	}

}
