package homework;

import java.util.Calendar;

public class Sale_Event {

	public static void main(String[] args) {
		
		//Calendar today = Calendar.getInstance();
		
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH);
		int day = today.get(Calendar.DAY_OF_MONTH);
		int hour = today.get(Calendar.HOUR_OF_DAY);
		if((day >= 1 && day <= 3) && 
				(hour >=13 && hour <=16)) { //
		if(year == 2021 && month == 0) { 
			System.out.println("초특가 이벤트!");
		}}else {
			System.out.println("환영합니다!");
		}
	}

}
