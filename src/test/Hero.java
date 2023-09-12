package test;


public class Hero {
	private String name;
	private int hp;
	private int mp;

	public Hero() {
	}

	public Hero(String name, int hp, int mp) {
		this.name = name;
		this.hp = hp;
		this.mp = mp;
	}

	public String getName() {
		return name;
	}
	public int getHp() {
		return hp;
	}
	public int getMp() {
		return mp;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}
}
