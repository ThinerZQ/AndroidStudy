package com.example.domain;

public class Person {
	

	public Person(int id, String name, String number) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
	}
	private int id;
	private String name;
	private String number;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", number=" + number
				+ "]";
	}
	
}
