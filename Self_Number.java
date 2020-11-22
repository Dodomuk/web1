package f_function;

import java.util.Scanner;

//d(1) = 1+1 = 2  => 2는 셀프넘버 x
//d(2) = 2+2 = 4  => 4는 셀프넘버 x
//d(39) = 39 + 3 + 9 = 51  => 51은 셀프넘버 x
//d(999) = 999 + 9 + 9 + 9 = 1026   => 1026은 셀프넘버 x
//d(9999) = 9999 + 9 + 9 + 9 + 9 = 10035 => 10035은 셀프넘버 x
//d(10000) = 10000 + 1 = 10001 => 10001은 셀프넘버 x

public class Self_Number {

	Self_Number sn = new Self_Number();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] a = new int[10000];
		for (int i = 1; i < a.length; i++) {
			for (int  j = 1; j < a.length; j++) {
		
				if(getSelfNum(j) == i) {
								a[i] = i;	
			}
			}
			
			if(a[i] != i) {
			System.out.println(i);
			}
			
		}
	}

	public static int getSelfNum(int i) {

		int d = 0;

		// 10000일 경우
		if (i == 10000) {
			d += i + 1;
		}
		// 천의 자리 식
		if (i >= 1000 && i < 10000) {
			d += i + (i - (i % 1000)) / 1000 + (i % 1000 - i % 100)
					/ 100 + (i % 100 - i % 10) / 10 + i % 10;
		}
		// 백의 자리 식
		if (i >= 100 && i < 1000) {
			d += i + (i - (i % 100)) / 100 + ((i % 100) - (i % 10)) / 10 + i % 10;
		}
		// 십의 자리 식
		if (i >= 10 && i < 100) {
			d += i + (i - i % 10) / 10 + i % 10;
		}
		// 일의 자리 식 (필요 없음)
		if (i < 10) {
			d += i + i;
		}

		return d;

	}
}
