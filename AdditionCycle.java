package b_while;

import java.util.Scanner;

public class AdditionCycle {

	  Scanner sc = new Scanner(System.in);
	  private int[] cyclingNum = new int[2];
	  private int sum = 0;
	  private int res;
	public static void main(String[] args) {
		
	AdditionCycle ac = new AdditionCycle();
	ac.numberAddition();
		
	}
	  
	  public void numberAddition() {
	  
	  System.out.print("숫자를 입력해주세요~ : "); 
	  int inputNumFirst = sc.nextInt();
	  int inputNum = inputNumFirst;
	  
	  int i = 0;
	  boolean flg = true;
	  
	  while(flg) {
		  
		  if(inputNum>= 10 && inputNum<=99) {
		  
		  cyclingNum[1] = inputNum % 10;  
		  cyclingNum[0] = (inputNum - cyclingNum[1])/10;
		  
		  }else if(inputNum < 10 && inputNum >= 0) {
			  
			  cyclingNum[0] = 0;
			  cyclingNum[1] = inputNum;
			  
		  }else {
		  
			  System.out.println("0에서 99 사이의 숫자를 입력해주세요");
			  return; 
			  
			  }
		 
			 sum = cyclingNum[0] + cyclingNum[1]; 
			 res = (cyclingNum[1] * 10) + (sum % 10);
			 System.out.println("새로운 수 :" + res);
				i++;		 			 			
				if(res == inputNumFirst) {
					flg = false;
				}else {
					inputNum = res;
				}
	 
	  }
	  System.out.println("총 사이클 길이는 :" + i + "입니다.");
	  
	  }
	 
}
