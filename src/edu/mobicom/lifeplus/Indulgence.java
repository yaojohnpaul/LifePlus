package edu.mobicom.lifeplus;

public class Indulgence {

	private int id;
	private String name;
	private String desc;
	private int price;
	
	public Indulgence() {
		id = -1;
		name = "";
		desc = "";
		price = 0;
	}
	
	public Indulgence(String name, String desc, int price) {
		id = -1;
		this.name = name;
		this.desc = desc;
		this.price = price;
	}
	
	public Indulgence(int id, String name, String desc, int price) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.price = price;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
}
