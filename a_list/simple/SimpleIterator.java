package com.kh.c_collection.a_list.simple;

import java.util.Iterator;

public class SimpleIterator<E> implements Iterator<E>{
	
	private Object[] simpleList;
	//배열안에 저장된 요소의 개수
	private int size;
	//Iterator가 읽은 데이터의 인덱스
	private int iterIdx;
	
	public SimpleIterator(Object[] simpleList, int size, int iterIdx) {
		super();
		this.simpleList = simpleList;
		this.size = size;
		this.iterIdx = iterIdx;
	}

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
