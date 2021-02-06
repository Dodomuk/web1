package com.kh.c_collection.a_list.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Run {

	public static void main(String[] args) {

		List<Student> studentList = new ArrayList<Student>();
		studentList.add(new Student("박미영", 17));
		studentList.add(new Student("김백관", 15));
		studentList.add(new Student("송명재", 27));
		studentList.add(new Student("박미영", 11));
		studentList.add(new Student("이지수", 22));
		studentList.add(new Student("정동묵", 29));
		studentList.add(new Student("조아영", 20));
		studentList.add(new Student("윤예나", 23));
		
		//이름 순 정렬
		studentList.sort(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				//o1의 이름이 o2보다 크면 양수, o1의 이름이 o2보다 작으면 음수, 같으면 0을 반환하는 String의 메서드
				//오름차순 정렬
				return o1.getName().compareTo(o2.getName());
				//내림차순 정렬
				//return -(o1.getName().compareTo(o2.getName()));
			}
		});
		for (Student student : studentList) {
			System.out.println(student);
		}
		//나이 순 정렬
		System.out.println("========================================");
		studentList.sort(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				//오름차순
				return o1.getAge() - o2.getAge();
				//내림차순
				//return -(o1.getAge() - o2.getAge());
			}
		});
		
		for (Student student : studentList) {
			System.out.println(student);
		}
		
		//이름을 기준으로 오름차순으로 정렬하되, 만약 같은 이름의 인스턴스가 존재하면 점수를 기준으로 오름차순 정렬 하세요.
		System.out.println("========================================");
		studentList.sort(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				int res = o1.getName().compareTo(o2.getName());
				//이름이 같지 않으면 compareTo 메서드의 결과를 반환
				if(res != 0){
					return res;
				//이름이 같으면 나이로 오름차순 한 결과값을 반환
				}else {
					return o1.getAge() - o2.getAge();
				}
			}
		});
		
		for (Student student : studentList) {
			System.out.println(student);
		}
		
		System.out.println("========================================");
		Collections.sort(studentList);
		for (Student student : studentList) {
			System.out.println(student);
		}
	}
}
