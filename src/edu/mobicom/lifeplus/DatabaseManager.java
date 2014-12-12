package edu.mobicom.lifeplus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {

	public DatabaseManager(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create Task Table
		Log.i("DATABASE", "onCreate()"); 
		db.execSQL("CREATE TABLE " + Task.TABLE_NAME + " (" + Task.COLUMN_ID
				+ " integer PRIMARY KEY autoincrement," + Task.COLUMN_NAME
				+ " text," + Task.COLUMN_DESC + " text," + Task.COLUMN_TIME
				+ " text," + Task.COLUMN_DATE + " text," + Task.COLUMN_TYPE
				+ " text," + Task.COLUMN_STATUS + " integer)");

		db.execSQL("CREATE TABLE " + Indulgence.TABLE_NAME + " ("
				+ Task.COLUMN_ID + " integer PRIMARY KEY autoincrement,"
				+ Indulgence.COLUMN_NAME + " text," + Indulgence.COLUMN_DESC
				+ " text," + Indulgence.COLUMN_PRICE + " integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	
	public void editTask(int id, Task newTask) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(Task.COLUMN_NAME, newTask.getName());
		values.put(Task.COLUMN_DESC, newTask.getDesc());
		values.put(Task.COLUMN_TIME, newTask.getTime());
		values.put(Task.COLUMN_STATUS, newTask.isChecked());
		values.put(Task.COLUMN_TYPE, newTask.getType());
		
		db.update(Task.TABLE_NAME, values, Task.COLUMN_ID + "= ?", new String[]{String.valueOf(id)});
		db.close();
	}
	
	public void setChecked(int id, Boolean isChecked) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(Task.COLUMN_STATUS, isChecked);
		
		db.update(Task.TABLE_NAME, values, Task.COLUMN_ID + "= ?", new String[]{String.valueOf(id)});
		db.close();
	}

	public void addTask(Task t) {
		SQLiteDatabase db = getWritableDatabase();

		ContentValues values = new ContentValues();
		
		values.put(Task.COLUMN_NAME, t.getName());
		values.put(Task.COLUMN_DESC, t.getDesc());
		values.put(Task.COLUMN_TIME, t.getTime());
		values.put(Task.COLUMN_STATUS, t.isChecked());
		values.put(Task.COLUMN_TYPE, t.getType());
		
		if(t.getType() == 2)
			values.put(Task.COLUMN_DATE, t.getDate().toString());

		db.insert(Task.TABLE_NAME, null, values);
		db.close();
	}

	public void deleteTask(int id) {
		SQLiteDatabase db = getWritableDatabase();
		db.delete(Task.TABLE_NAME, Task.COLUMN_ID + " =? ",
				new String[] { String.valueOf(id) });
		db.close();
	}
	
	public void setAsDone(int id) {
		SQLiteDatabase db = getWritableDatabase();
//		db.delete(Task.TABLE_NAME, Task.COLUMN_ID + " =? ",
//				new String[] { String.valueOf(id) });
		db.close();
	}

	public ArrayList<Task> getDailyQuests() {
		
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.query(Task.TABLE_NAME, /*new String[] { Task.COLUMN_NAME,
				Task.COLUMN_DESC, Task.COLUMN_TIME, Task.COLUMN_STATUS }*/ null, Task.COLUMN_TYPE + " = ?",
				new String[] { "1" }, null, null, null);

		ArrayList<Task> daily = new ArrayList<Task>();
		
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(Task.COLUMN_ID));
				String name = c.getString(c.getColumnIndex(Task.COLUMN_NAME));
				String desc = c.getString(c.getColumnIndex(Task.COLUMN_DESC));
				String time = c.getString(c.getColumnIndex(Task.COLUMN_TIME));
				
				int status = c.getInt(c.getColumnIndex(Task.COLUMN_STATUS));
				boolean isChecked;
				if(status == 1){
					isChecked = true;
				}
				else{
					isChecked = false;
				}
					
				daily.add(new Task(id, name, desc, time, 1, isChecked));
			} while (c.moveToNext());
		}

		c.close();
		db.close();
		
		return daily;
	}
	
	public ArrayList<Task> getTodoList() {
		
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.query(Task.TABLE_NAME, null, Task.COLUMN_TYPE + " = ?",
				new String[] { "2" }, null, null, null);

		ArrayList<Task> todo = new ArrayList<Task>();
		
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(Task.COLUMN_ID));
				String name = c.getString(c.getColumnIndex(Task.COLUMN_NAME));
				String desc = c.getString(c.getColumnIndex(Task.COLUMN_DESC));
				String time = c.getString(c.getColumnIndex(Task.COLUMN_TIME));
				Date date = null;
				
				try {
					date = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH)
							.parse(c.getString(c
									.getColumnIndex(Task.COLUMN_DATE)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				int status = c.getInt(c.getColumnIndex(Task.COLUMN_STATUS));
				boolean isChecked;
				if(status == 1){
					isChecked = true;
				}
				else{
					isChecked = false;
				}
					
				todo.add(new Task(id, name, desc, date, time, 2, isChecked));
			} while (c.moveToNext());
		}

		c.close();
		db.close();
		
		return todo;
	}
}

	

	