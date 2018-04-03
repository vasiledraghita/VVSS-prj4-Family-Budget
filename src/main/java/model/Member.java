package model;

public class Member {
	private String name;
	private int id;

	public Member(String name) {
		this.name = name;
	}

	public Member(String name, int id){
		this.name= name;
		this.id=id;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	
	public String toString() {
		return "name: " + this.name + " idM=" + this.id;
	}
}
