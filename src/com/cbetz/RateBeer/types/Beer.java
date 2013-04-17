package com.cbetz.RateBeer.types;

public class Beer {
	private int mId;
	private String mName;
	
	public Beer(int id, String name) {
		setId(id);
		setName(name);
	}

	public void setName(String name) {
		this.mName = name;
	}

	public String getName() {
		return mName;
	}

	public void setId(int id) {
		this.mId = id;
	}

	public int getId() {
		return mId;
	}
}
