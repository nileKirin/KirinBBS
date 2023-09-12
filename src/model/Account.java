package model;

import java.io.Serializable;

public class Account implements Serializable{
	private int id;
	private String userId;
	private String pass;
	private int age;
	private String email;
	public Account() {}
	public Account(String userId, String pass, String email) {
		this.userId = userId;
		this.pass = pass;
		this.email = email;		
	}
	public Account(String userId, String pass, int age, String email){
		this.userId = userId;
		this.pass = pass;
		this.age = age;
		this.email = email;
	}
	public Account(int id, String userId, String pass, int age, String email){
		this.id = id;
		this.userId = userId;
		this.pass = pass;
		this.age = age;
		this.email = email;
	}
	public int getId() {return id;}
	public String getUserId() {return userId;}
	public String getPass() {return pass;}
	public int getAge() {return age;}
	public String getEmail() {return email;}
}
