package com.kh.c_collection.a_list.simple;

import java.util.Iterator;

//Generic : 클래스 내부에서 사용하는 타입을 외부에서 결정하는 것. 인스턴스화 할 때 결정된다.
//클래스명 뒤에 <E>와 같은 형태로 Generic을 지정할 수 있다.
//Generic을 지정하면 해당 클래스 내에서 Generic 타입을 사용할 수 있다.
//Generic은 어떤 알파벳으로든 사용이 가능하지만 주로 아래의 알파벳이 많이 사용된다.
//E : Element, 배열 기반의 구조에서 Generic을 사용할 경우 주로 E로 지정
//T : 일반적인 Type의 의미
//K : Map에서 Key의 타입을 Generic으로 지정할 경우 많이 사용
//V : Map에서 Value의 타입을 Generic으로 지정할 경우 많이 사용

//Generic을 선언할 때 제네릭 타입에 제한을 걸 수 있다.
//<? extends Type> : Type의 후손 타입만 Generic으로 지정가능
//<? super Type> : Type의 부모 타입만 Generic으로 지정가능
//public class SimpleList<E extends List> {

public class SimpleList<E> implements Iterable<E>{
	//Object[] 배열의 초기 크기
	//배열이 가득차면 *2 만큼 확장
	private int arraySize = 11;
	private Object[] simpleList;
	//배열안에 저장된 요소의 개수
	private int size = 0;
	
	public SimpleList() {
		simpleList = new Object[arraySize];
	}
	
	//매개변수로 넘어온 크기로 Object[] 생성
	public SimpleList(int arraySize) {
		simpleList = new Object[arraySize];
	}
	
	//size()
	public int size() {
		return size;
	}
	
	//add(E data)
	//List의 끝에 요소를 추가하는 메서드
	public void add(E data) {
		//현재 배열이 다 차지 않은 경우
		if(size < arraySize) {
			simpleList[size] = data;
		//현재 배열이 다 찬 경우	
		}else {
			//배열의 크기를 두배로 확장
			arraySize *= 2;
			Object[] tempArr = new Object[arraySize];
			//기존 배열에서 임시 배열로 데이터 복제
			for(int i = 0; i < simpleList.length; i++) {
				tempArr[i] = simpleList[i];
			}
			tempArr[size] = data;
			//데이터를 추가한 배열을 다시 simpleList 레퍼런스에 옮겨준다.
			simpleList = tempArr;
		}
		
		//size 1 증가
		size++;
	}
	
	//get(int idx)
	public E get(int idx) {
		return (E)simpleList[idx];
	}
	
	//set(int idx, E data)
	//지정된 위치의 데이터를 교체하고 교체되기전 요소를 반환
	public E set(int idx, E data) {
		//교체되기전 요소 저장
		E res = (E)simpleList[idx];
		simpleList[idx] = data;
		return res;
	}
	
	//remove(int idx)
	public E remove(int idx) {
		//삭제될 요소 저장
		E res = (E)simpleList[idx];
		
		for(int i = 0; i < size - 1; i++) {
			//삭제하고 뒤의 요소들을 앞으로 한칸씩 땡겨준다.
			if(i >= idx) {
				simpleList[i] = simpleList[i+1];
			}
		}
		
		//마지막 인덱스를 null로 채워준다.
		simpleList[size-1] = null;
		//삭제했음으로 size - 1
		size--;
		return res;
	}

	@Override
	public Iterator<E> iterator() {
		System.out.println("forEach가 iterator 호출");
		//상속이나 구현을 통해 물려받는 메서드만 사용하는 클래스의 경우
		//필요하다면 익명클래스로 생성이 가능하다.
		//익명클래스는 부모 또는 인터페이스의 타입명(){} 와 같은 형식으로 생성할 수 있다.
		return new Iterator<E>() {
			//Iterator가 읽은 데이터의 인덱스
			private int iterIdx;
			
			//Object[] 안에 데이터가 남아있는지 판단하는 메서드
			@Override
			public boolean hasNext() {
				//iterIdx(마지막으로 next()를 통해 반환한 요소의 인덱스 + 1)
				//가 size보다 작으면 true
				if(iterIdx < size) {
					System.out.println("forEach가 hasNext 호출");
					return true;
				}
				return false;
			}
			
			//Object[] 안의 데이터들을 순회하면서 반환하는 메서드
			@Override
			public E next() {
				Object res = simpleList[iterIdx];
				System.out.println("forEach가 next 호출");
				iterIdx++;
				return (E)res;
			}
		};
	}
	
	//내부클래스 (Inner Class, Memeber Class)
	//클래스 안에 클래스를 선언할 수 있다.
	//바깥쪽 클래스의 필드를 공유해서 사용할 수 있다.
	private class SimpleIterator<E> implements Iterator<E>{
		
		//Iterator가 읽은 데이터의 인덱스
		private int iterIdx;
		
		//Object[] 안에 데이터가 남아있는지 판단하는 메서드
		@Override
		public boolean hasNext() {
			//iterIdx(마지막으로 next()를 통해 반환한 요소의 인덱스 + 1)
			//가 size보다 작으면 true
			if(iterIdx < size) {
				System.out.println("forEach가 hasNext 호출");
				return true;
			}
			
			return false;
		}
		
		//Object[] 안의 데이터들을 순회하면서 반환하는 메서드
		@Override
		public E next() {
			Object res = simpleList[iterIdx];
			System.out.println("forEach가 next 호출");
			iterIdx++;
			return (E)res;
		}
	}
	

}
