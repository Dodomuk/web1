package com.kh.collection.setExample.run;

import java.util.ArrayList;


/*[컬렉션 연습]

<문제 1>
:Set 계열 클래스와 Random 사용
-클래스명 : com.kh.collection.setExample.run.Run.java
             >>main() 메소드 포함
-메소드 추가 : public static void lottoDisplay(){}
             
        >>구현 내용
        1~45 사이의 정수를 중복되지 않게, 6개 발생시켜
        ArrayList<int>에 담아 오름차순으로 정렬하여 출력하시오*/


public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Run ld = new Run();
		ld.lottoDisplay();
	}
	public static void lottoDisplay() {
		
		ArrayList<Integer> lottoNum = new ArrayList<Integer>();
		for (int i  = 0; i  < 6; i ++) {
			lottoNum.add((int)(Math.random()*45)+1);
		}
		System.out.println(lottoNum);
	}

}

