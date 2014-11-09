package edu.mobicom.lifeplus;

public class Task {

	private int itemID;
	private String itemName;
	private String itemDesc;
	private Boolean isChecked;
	
	public Task() {
		itemID = -1;
		itemName = "";
		itemDesc = "";
		isChecked = false;
	}
	
	public Task(String itemName, String itemDesc, Boolean isChecked) {
		itemID = -1;
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.isChecked = isChecked;
	}
	
	public Task(int itemID, String itemName, String itemDesc, Boolean isChecked) {
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.isChecked = isChecked;
	}
	
	public int getID() {
		return itemID;
	}
	
	public String getName() {
		return itemName;
	}
	
	public String getDesc() {
		return itemDesc;
	}
	
	public Boolean isChecked() {
		return isChecked;
	}
	
	public void setID(int itemID) {
		this.itemID = itemID;
	}
	
	public void setName(String itemName) {
		this.itemName = itemName;
	}
	
	public void setDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	
	public void setChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}
	
}
