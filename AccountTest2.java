package account;

public class AccountTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Account[] acnt = new Account[5];
		
		for (int i = 0; i < acnt.length; i++) {
			
			acnt[i] = new Account("221-0101-211"+Integer.toString(i+1)
			,100000,4.5);
	
			
			acnt[i].accountInfo(); //계좌 정보 출력
			System.out.println(" ");
		}
		
		for (int j = 0; j < acnt.length; j++) {
			
			acnt[j] = new Account("221-0101-211"+Integer.toString(j+1)
			,100000,3.7);
			
			acnt[j].accountInfo();
			System.out.println(" 이자: " + (int)(acnt[j].calculateInterest()) + "원");
			
		}
	
	}

}
