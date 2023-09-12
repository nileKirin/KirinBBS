package model;

import java.io.Serializable;

public class Mutter implements Serializable{
	private int id;
	private String userId;
	private String content;
	private String time;

	public Mutter() {
	}
	public Mutter(String userId, String content){
		this.userId = userId;
		this.content = content;
	}
	public Mutter(String userId, String content, String time){
		this.userId = userId;
		this.content = content;
		this.time = time;
	}

	public Mutter(int id, String userId, String content, String time){
		this.id= id;
		this.userId = userId;
		this.content = content;
		this.time = time;
	}

	public int getId() {
		return id;
	}
	public String getUserId() {
		return userId;
	}
	public String getContent() {
		return content;
	}
	public String getTime() {
		return time;
	}
}

