package edu.mobicom.lifeplus;

import java.util.Date;

public class Task {

	static final String TABLE_NAME = "TaskList";
	static final String COLUMN_ID = "_id";
	static final String COLUMN_NAME = "name";
	static final String COLUMN_DESC = "desc";
	static final String COLUMN_DATE = "date";
	static final String COLUMN_TIME = "time";
	static final String COLUMN_TYPE = "type";
	static final String COLUMN_STATUS = "status";
	static final String DATABASE_NAME = "LifePlus";
	
	private int id;
	private String name;
	private String desc;
	private Date date;
	private String	time;
	private int type; // 1 daily, 2 normal task
	private Boolean isChecked;
	private String difficulty;
	
	public Task() {
		id = -1;
		name = "";
		desc = "";
		isChecked = false;
	}
	
	public Task(String name, String desc, Date date, String time, int type, Boolean isChecked) {
		id = -1;
		this.name = name;
		this.desc = desc;
		this.date = date;
		this.time = time;
		this.type = type;
		this.isChecked = isChecked;
	}
	
	public Task(String name, String desc, String time, int type, Boolean isChecked) {
		id = -1;
		this.name = name;
		this.desc = desc;
		this.date = null;
		this.time = time;
		this.type = type;
		this.isChecked = isChecked;
	}
	
	public Task(int id, String name, String desc, Date date, String time, int type, Boolean isChecked) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.date = date;
		this.time = time;
		this.type = type;
		this.isChecked = isChecked;
	}
	
	public Task(int id, String name, String desc, String time, int type, Boolean isChecked) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.date = null;
		this.time = time;
		this.type = type;
		this.isChecked = isChecked;
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
	
	public String getTime() {
		return time;
	}
	
	public Date getDate() {
		return date;
	}
	
	public int getType() {
		return type;
	}
	
	public Boolean isChecked() {
		return isChecked;
	}
	
	public String getDifficulty() {
		return difficulty;
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
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public void setChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}
	
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	
}
