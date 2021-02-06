package com.kh.c_collection.a_list;

import java.util.LinkedList;

import com.kh.c_collection.a_list.simple.SimpleLinkedList;
import com.kh.c_collection.a_list.simple.SimpleList;
import com.kh.model.vo.Music;

public class Run {

	public static void main(String[] args) {
		ListTest lt = new ListTest();
		//lt.doList();
		
		SimpleLinkedList<Music> list = new SimpleLinkedList<>();
		list.add(new Music("백지영","총맞은것처럼"));
		list.add(new Music("김경호","금지된사랑"));
		list.add(new Music("윤도현","나는나비"));
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		list.set(2, new Music("아이유","BOO"));
		System.out.println("\n" + list.get(2));
		
		System.out.println("\\\\\\\\\\\\\\\\\\\\\\");
		list.remove(1);
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
	   System.out.println("\niterable 구현 후 for-each문 실행");
	   
	   for(Music m : list) {
		  System.out.println(m);
	   }
		 
		 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
