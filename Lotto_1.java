package homework;

import java.util.Arrays;

public class Lotto_1 {

	public static void main(String[] args) {

		int[] arr = new int[6];
		for (int i = 0; i < 6; i++) {
			arr[i] = (int) (Math.random() * 45 + 1);
           
			if(i>0) {
			for (int j = 0; j < i; j++) {
				if(arr[i] == arr[j]) {
					i--;
					break;
				}
			}
			}
			
		}
		Arrays.sort(arr);
	
		System.out.println(Arrays.toString(arr));
	}

}
