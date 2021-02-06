package com.kh.c_collection.a_list;

import java.util.LinkedList;
import java.util.List;

import com.kh.model.vo.Music;

public class ListTest {
	
	//List : 데이터 저장을 위해 제공되는 자료구조 클래스
	//		Object[]로 이루어져 있다.
	
	public void doList() {
		//Generic : 클래스 내부에서 사용하는 타입을 외부에서 결정하는 것. 
		//       인스턴스화 할 때 결정된다.
		//		 Generic에 타입을 지정하지 않으면 Object타입으로 지정된다.
		//		 자바 1.7버전 부터 Generic 타입 유추 기능이 도입
		//List<Music> list = new ArrayList<Music>();
		//List<Music> list = new ArrayList<>();
		//다형성
		List<Music> list = new LinkedList<>();
		
		//1. list의 크기를 출력
		System.out.println("list의 크기 : " + list.size());
		//CRUD : Create(생성), Read(읽기), Update(수정), Delete(삭제)
		
		//2.list의 제일 끝에 데이터 추가
		//new Music("백지영","총맞은것처럼")
		//new Music("김경호","금지된사랑")
		//new Music("윤도현","나는나비")
		list.add(new Music("백지영","총맞은것처럼"));
		list.add(new Music("김경호","금지된사랑"));
		list.add(new Music("윤도현","나는나비"));
		//for-each문 사용가능!
		for(Music m : list) {
			System.out.println(m);
		}
		
		//3.2번 인덱스에 데이터 추가하기
		//new Music("자우림","스물다섯,스물하나")
		list.add(2, new Music("자우림","스물다섯,스물하나"));
		System.out.println(list);
		
		//4. 2번 인덱스의 데이터를 반환 받아 
		//   반환 받은 후 출력해주세요
		//generic 지정 전 : Object
		//generic 지정 후 : Music
		Music music = list.get(2);
		System.out.println(music);
		
		//5. 2번 인덱스의 데이터를 수정
		//new Music("아이유","너랑나")
		list.set(2, new Music("아이유","너랑나"));
		System.out.println(list);
		
		//6. 2번 인덱스 삭제
		//	삭제 후 list의 크기를 출력해서 확인해보기
		list.remove(2);
		System.out.println("크기 : " + list.size() + " / 데이터" + list);
		
		//7. new Music("백지영","총맞은것처럼") 데이터가 list에 있는지 확인해보기
		//	응용 : 만약 예상한대로 결과가 나오지 않을 경우 Music클래스를 수정해
		//		예상한 결과값 받아보기
		System.out.println("7번 : " + list.contains(new Music("백지영","총맞은것처럼")));
		
		//8. new Music("백지영","총맞은것처럼") 데이터를 가지고 있는 인덱스 반환
		System.out.println("new Music(\"백지영\",\"총맞은것처럼\") : " 
					+ list.indexOf(new Music("백지영","총맞은것처럼")));
		
		//9. list의 0번 인덱스부터 2번 인덱스 앞(1번 인덱스까지)의 데이터를 잘라내
		//	새로운 ArrayList로 반환받아 출력
		List<Music> subList = list.subList(0, 2);
		System.out.println(subList);
		//* 노란 줄은 신경쓰지 않으셔도 괜찮습니다.
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
