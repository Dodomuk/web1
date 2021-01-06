package j_factorial;

import java.util.Scanner;

public class Pavonacci {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int pavonacci = 0;
		int res = 1;
		if (n >= 2) {
			for (int i = 0; i < n - 1; i++) {

				res = res + pavonacci;
				pavonacci = res - pavonacci;

			}

		} else {

			if (n == 1) {
				res = 1;
			} else {
				res = 0;
			}

		}
		System.out.println(res);
	}

}
