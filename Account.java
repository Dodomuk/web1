package account;

public class Account {

	private String account;
	private int balance;
	private double interestRate;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(String account, int balance, double interestRate) {
		super();
		this.account = account;
		this.balance = balance;
		this.interestRate = interestRate;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double calculateInterest() {
		
		interestRate = (getBalance() * getInterestRate())/100;
		
		return interestRate;
		
	}
	
	public void deposit(int money) {
		
		balance = getBalance() + money; 
		
	}
	
	public void withdraw(int money) {
		
		balance = getBalance() - money; 
		
	}
	
	public void accountInfo() {
		System.out.print("계좌번호: " + getAccount() + " 잔액: " + getBalance() 
		+ " 이자율: " + getInterestRate() + "%");
	}
	
}
