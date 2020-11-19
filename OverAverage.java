package a_oneDimensionalArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class OverAverage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader
				(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		try {
			
			StringTokenizer st;
			
			int c = Integer.parseInt(br.readLine()); // 입력 받을 테스트 케이스의 개수
			int[] cArr = new int[c]; // 테스트 케이스만큼 점수를 입력하기 위한 배열

			for (int i = 0; i < cArr.length; i++) {
				
			    int overAvg = 0; //평균을 넘는 학생의 수

				st = new StringTokenizer(br.readLine()," ");
				
				cArr[i] = Integer.parseInt(st.nextToken());//학생들
				
		
				
				int[] nArr = new int[cArr[i]]; // 학생의 점수들
				int sum = 0; // 점수 합계
				
				for (int j = 0; j < nArr.length; j++) {
					nArr[j] = Integer.parseInt(st.nextToken());
					sum += nArr[j];

				}
				
				double avg = sum/nArr.length; //평균 점수 산출
				
				for (int k = 0; k < nArr.length; k++) { //점수 평균을 넘는 갯수를 구하기 위한 for문 
		
					if(nArr[k] > avg) {					
						overAvg ++;
								
				}
				}
				//첫번째 *100은 기준을 0% ~ 100% 사이로 잡기 위함, 두번째 *1000은 Math.round 후 정수로 반환되면 다시 1000으로 나눠
				//소숫점 뒤에 3자리를 만들어 놓기 위함
				double overAvgs = (double)Math.round(((double)overAvg / (nArr.length))*100*1000)/1000; 
				
	         			//소수점 3자리 표현식
						sb.append(overAvgs + "%");	
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
