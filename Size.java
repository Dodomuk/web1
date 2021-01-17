package k_bruteForce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Size {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); //총 인원 수 입력
		
		int[][] profile = new int[n][2];  //1번째 배열: 인원 , 2번째 배열:키 몸무게
		
		for (int k = 0; k < n; k++) {  // 0값에 키, 1값에 몸무게 삽입
			
			profile[k][0] = sc.nextInt();
			profile[k][1] = sc.nextInt();
	
		}
		
		for (int i = 0; i <n; i++) {
			int rank = 1;
			for (int j = 0; j < n; j++) {
		  
				if((profile[i][0] < profile[j][0]) && (profile[i][1] < profile[j][1])){
					rank++;
				}
					
		}	
			System.out.print(rank + " ");
		}
		
		
		
	}

}
