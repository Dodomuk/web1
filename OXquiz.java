package a_oneDimensionalArray;


import java.util.Scanner;

public class OXquiz {
	
	Scanner sc = new Scanner(System.in);
	int[] OXArr = new int[10];
	int score = 0;
	boolean flg = true;
	public OXquiz() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void OXvalid() {
		
		//사용자로부터 10 크기의 OX가 담긴 문자열을 받는다
		System.out.println("OX를 표시해 주세요 : ");
		String inputox = sc.nextLine();
		
		for (int i = 0; i < 10; i++) {
					
			if((char)(inputox.charAt(i)) == 'O') {
			OXArr[i] = 1;
			
			for (int j = 1; j < OXArr.length-1; j++) {
				//첫번째 조건이 먼저 오지 않으면 오류가 남 => charAt() 안에 음수가 들어 오면 안되기 떄문에 i-j가 음수인지를 먼저
				//파악해주고 비교해야함
				if( i-j >= 0 && inputox.charAt(i) == inputox.charAt(i-j)) { 
					OXArr[i] = 1+j;
				}else{	
					break;
				}		
			}					
			}
			}
		for (int k = 0; k < OXArr.length; k++) {
			score += OXArr[k];
		}
		System.out.println("나의 점수는 ? : " + score);	
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		OXquiz oq = new OXquiz();
		oq.OXvalid();
	}


}
