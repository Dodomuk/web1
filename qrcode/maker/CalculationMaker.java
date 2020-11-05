package com.kh.qrcode.maker;

public class CalculationMaker {

	public void Calculator(int num_1,int num_2, char operator){
		
		switch(operator) {
		
		case '+' : System.out.println("결과 :" + (num_1 + num_2));
		break;
		case '-' : System.out.println("결과 :" + (num_1 - num_2));
		break;
		case '*' : System.out.println("결과 :" + (num_1 * num_2));
		break;
		case '/' : System.out.println("결과 :" + (num_1 / num_2));
		break;
		case '%' : System.out.println("결과 :" + (num_1 % num_2));
		break;
		default : System.out.println("올바른 문자를 입력해주세요");	
		
		}
		
	}
}
