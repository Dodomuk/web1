package account;

public class AccountTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//계좌 생성
		Account acnt = new Account();
				acnt.setAccount("441-0290-1203");
				System.out.println("계좌정보: " +acnt.getAccount() + " 현재잔액: "
						+ acnt.getBalance());
				acnt.deposit(20000);
				System.out.println("계좌정보: " + acnt.getAccount() + " 현재잔액: "
						+ acnt.getBalance());
				System.out.println("이자: " + acnt.calculateInterest());
		
	}

}
