package com.kh.c_collection.a_list.sort;

public class Student implements Comparable<Student>{
	
	private String name;
	private int age;
	
	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}

	//이름으로 내림차순 정렬하게끔 오버라이드
	@Override
	public int compareTo(Student o) {
		return -(this.getName().compareTo(o.getName()));
	}
	
	
	

}
