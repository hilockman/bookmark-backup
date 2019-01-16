package org.study.reddisson;

public class SomeObject {
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public SomeObject(int id) {
		this.id = id;
	}
	
	public SomeObject() {
		this.id = -1;
	}

	@Override
	public String toString() {
		return "SomeObject [id=" + id + "]";
	}
}