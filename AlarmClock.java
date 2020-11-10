package d_if;

import java.util.Scanner;

public class AlarmClock {

	Scanner sc= new Scanner(System.in);
	private int h;
	private int m;
	private boolean flg = true;
	public static void main(String[] args) {
		
		AlarmClock ac = new AlarmClock();
		ac.alarmChecker();
	}
	
	public void alarmChecker() {

		while(true) {
		//두개의 숫자를 입력 받는다(시, 분)
		int input_h = sc.nextInt();
		int input_m = sc.nextInt();
		//if문을 사용해 시 분이 조건을 만족하도록한다 (0 ≤ H ≤ 23, 0 ≤ M ≤ 59).

		if(input_h > 23 || input_h < 0)
		{
			System.out.println("다시 입력하세요");
			break;
		}else if(input_m > 59 || input_m < 0) {
			System.out.println("다시 입력하세요");
			break;
		}
		//입력한 시간에서 45분을 빼는 식을 만든다.
		h = input_h;
		m = input_m - 45;
		//만약 H가 0일때 M이 45보다 작다면 H이 23이 되도록 한다.
		if(m<0) {
			m = 60 + m;
			h = input_h -1 ;
		   if(h < 0) {
			   h = 23;
		   }
		}
		
		System.out.println(h + " " + m);
	}
}
}
