package com.example.smartactions;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventsDatabase extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_NAME = "EventsDatabase";
	private static final String NAME = "eventname";
	private static final String RINGVOL = "volume";
	private static final String MODE = "mode";
	private static final String ACTIVE = "active";
	private static final String BTOOTH = "btooth";
	private static final String WIFI= "wifi";
	private static final String BRIGHT = "brightness";
	private static final String PLAYER = "player";
	
	
	
	
	
	
	
	public EventsDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_DATA = "CREATE TABLE Profile "  + "("+ NAME+","+RINGVOL+","+MODE+","+ACTIVE+")";
		db.execSQL(CREATE_CONTACTS_DATA);
		ContentValues values = new ContentValues();
		values.put(NAME, "Loud"); // Contact Name
		values.put(RINGVOL, "7");
		values.put(MODE, "N");
		values.put(ACTIVE,"0");
		db.insert("Profile", null, values);
		values.put(NAME, "Normal"); // Contact Name
		values.put(RINGVOL, "5");
		values.put(MODE, "N");
		values.put(ACTIVE,"1");
		db.insert("Profile", null, values);
		values.put(NAME, "Quiet"); // Contact Name
		values.put(RINGVOL, "1");
		values.put(MODE, "N");
		values.put(ACTIVE,"0");
		db.insert("Profile", null, values);
		values.put(NAME, "Silent"); // Contact Name
		values.put(RINGVOL, "0");
		values.put(MODE, "S");
		values.put(ACTIVE,"0");
		db.insert("Profile", null, values);
		values.put(NAME, "Vibrate"); // Contact Name
		values.put(RINGVOL, "0");
		values.put(MODE, "V");
		values.put(ACTIVE,"0");
		db.insert("Profile", null, values);
	}
	public void delProfile(String name)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		String where = NAME+" = ?";
		db.delete("Profile", where, new String[] {name});
		db.close();
	}
	// Adding new contact
	public void addContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(NAME, contact.getName()); // Contact Name
		values.put(RINGVOL, contact.getRingvol());
		values.put(MODE, contact.getMode());
		values.put(ACTIVE, contact.getAct());
		db.insert("Profile", null, values);
		db.close(); // Closing database connection
	}
	
	Contact getCont(String name){

		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery("Select * from "+ "Profile" + " where profilename = ? ",new String[] {name});
		if (cursor.getCount()!=0)
		{
			cursor.moveToFirst();
			Contact contact = new Contact(
					cursor.getString(0), Integer.parseInt(cursor.getString(1)),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),Integer.parseInt(cursor.getString(7)));
			return contact;
		}
		else{
			return null;
		}	
		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL("DROP TABLE IF EXISTS " + "Profile");
		onCreate(db);
		
	}

	public ArrayList<Contact> getAllProfiles() {
		// TODO Auto-generated method stub
		ArrayList<Contact> userList = new ArrayList<Contact>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor=db.rawQuery("SELECT * FROM Profile",null);
		if (cursor.getCount()!=0) {
			cursor.moveToFirst();
			do {
				Contact user = new Contact();
				user.name =cursor.getString(0);
				user.ringvol=Integer.parseInt(cursor.getString(1));
				user.mode= cursor.getString(2);
				user.act=cursor.getString(3);
				userList.add(user);
			} while (cursor.moveToNext());
			
		}
		db.close();
		return userList;
	}
	/*
	public ArrayList<User> getByUser(String name, String useb) {
		// TODO Auto-generated method stub
		ArrayList<User> userList = new ArrayList<User>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor=db.rawQuery("SELECT * FROM "+ TABLE_DATA+ " WHERE user = ? and username =  ? order by year desc,month desc,day desc", new String[]{name,useb});
		if (cursor.getCount()!=0) {
			cursor.moveToFirst();
			do {
				User user = new User();
				user.username =cursor.getString(0);
				user.title=cursor.getString(1);
				user.amount= cursor.getString(2);
				user.userpay = cursor.getString(3);
				user.day = cursor.getInt(4);
				user.month = cursor.getInt(5);
				user.year = cursor.getInt(6);
				user.notes=cursor.getString(7);
				userList.add(user);
			} while (cursor.moveToNext());
		}
		db.close();
		return userList;
	}
	
	
	public ArrayList<User> getBetweenDates(String name, int day1, int month1,int year1,int day2,int month2,int year2) {
		// TODO Auto-generated method stub
		ArrayList<User> userList = new ArrayList<User>();
		SQLiteDatabase db = this.getWritableDatabase();
		String q2 ="Select * from "+TABLE_DATA +" where year >=" + year1;
		String q1 = "Select * from (" +q2+ ") where month >=  "+month1;
		String q3 = "SELECT * FROM ("+ q1+ ") where day>= "+day1;
		String q4 = "SELECT * FROM ("+ q3+ ") where year <= "+year2;
		String q5 = "Select * from (" +q4+ ") where month <=  "+month2;
		Cursor cursor=db.rawQuery("SELECT * FROM ("+ q5+ ") where username = ? and day <= ? order by year desc,month desc,day desc", new String[]{name,Integer.toString(day2)});
		if (cursor.getCount()!=0) {
			cursor.moveToFirst();
			do {
				User user = new User();
				user.username =cursor.getString(0);
				user.title=cursor.getString(1);
				user.amount= cursor.getString(2);
				user.userpay = cursor.getString(3);
				user.day = cursor.getInt(4);
				user.month = cursor.getInt(5);
				user.year = cursor.getInt(6);
				user.notes=cursor.getString(7);
				userList.add(user);
			} while (cursor.moveToNext());
		}
		db.close();
		return userList;
	}
	
	public ArrayList<User> getByTitle(String title, String use) {
		// TODO Auto-generated method stub
		ArrayList<User> userList = new ArrayList<User>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor=db.rawQuery("SELECT * FROM "+ TABLE_DATA+ " WHERE title = ? and username = ? order by year desc,month desc,day desc", new String[]{title,use});
		if (cursor.getCount()!=0) {
			cursor.moveToFirst();
			do {
				User user = new User();
				user.username =cursor.getString(0);
				user.title=cursor.getString(1);
				user.amount= cursor.getString(2);
				user.userpay = cursor.getString(3);
				user.day = cursor.getInt(4);
				user.month = cursor.getInt(5);
				user.year = cursor.getInt(6);
				user.notes=cursor.getString(7);
				userList.add(user);
			} while (cursor.moveToNext());
		}
		db.close();
		return userList;
	}

	public void del(String use, String s2, String s3, String s4, String s5) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		String d,m,y;
		StringTokenizer st=new StringTokenizer(s4,"-",false);
		d=st.nextToken();
		m=st.nextToken();
		y=st.nextToken();
		String m1=s5.substring(7);
		String am=s3.substring(0);
		db.delete(TABLE_DATA, "username = ? and amount= ? and title=? and user=? and day= ? and month= ? and year= ? ", new String[] {use,am,m1,s2,d,m,y});
		db.close();
	}
	public ArrayList<User> getUserTitle(String useruser, String usertitle, String chk) {
		// TODO Auto-generated method stub
		ArrayList<User> userList = new ArrayList<User>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor=db.rawQuery("SELECT * FROM "+ TABLE_DATA+ " WHERE title = ? and user = ? and username=? order by year desc,month desc,day desc", new String[]{usertitle,useruser,chk});
		if (cursor.getCount()!=0) {
			cursor.moveToFirst();
			do {
				User user = new User();
				user.username =cursor.getString(0);
				user.title=cursor.getString(1);
				user.amount= cursor.getString(2);
				user.userpay = cursor.getString(3);
				user.day = cursor.getInt(4);
				user.month = cursor.getInt(5);
				user.year = cursor.getInt(6);
				userList.add(user);
				Log.d(userList.toString(), "sad");
			} while (cursor.moveToNext());
		}
		db.close();
		return userList;
	}
*/
	public void activateProfile(String str) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		String where = NAME+" = ?";
		ContentValues dataToInsert = new ContentValues();
		dataToInsert.put("active", "1");
		db.update("Profile", dataToInsert,where, new String[] {str});
		db.close();
	}
	public void updContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = NAME+" = ?";
		ContentValues values = new ContentValues();
		values.put(RINGVOL, contact.getRingvol());
		values.put(MODE, contact.getMode());
		values.put(ACTIVE, contact.getAct());
		db.update("Profile", values,where, new String[]{contact.getName()});
		db.close(); // Closing database connection
	}
	public void deactivateProfile(String str) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		String where = NAME+" = ?";
		ContentValues dataToInsert = new ContentValues();
		dataToInsert.put("active", "0");
		db.update("Profile", dataToInsert,where, new String[] {str});
		db.close();
	}
	

	public String retact(String string) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery("Select * from Profile where profilename = ? ",new String[] {string});
			cursor.moveToFirst();
			
			return cursor.getString(3);
	}
/*
	public String retdaysum(String string, int dd, int mm, int yy) {
		// TODO Auto-generated method stub
		int sum=0;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("Select * from "+ TABLE_DATA + " where username = ? and day= ? and month=? and year=?",new String[] {string,Integer.toString(dd),Integer.toString(mm),Integer.toString(yy)});
		if (cursor.getCount()!=0)
		{
			cursor.moveToFirst();
			do
			{	
				sum+=Integer.parseInt(cursor.getString(2));
			}while(cursor.moveToNext());	
			return Integer.toString(sum);
		}
		else
			return "0";
	}

	public String retmonthsum(String string, int mm, int yy) {
		// TODO Auto-generated method stub
		int sum=0;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("Select * from "+ TABLE_DATA + " where username = ? and month=? and year=?",new String[] {string,Integer.toString(mm),Integer.toString(yy)});
		if (cursor.getCount()!=0)
		{
			cursor.moveToFirst();
			do
			{	
				sum+=Integer.parseInt(cursor.getString(2));
			}while(cursor.moveToNext());	
			return Integer.toString(sum);
		}
		else
			return "0";
	}

	public String retyearsum(String string, int yy) {
		// TODO Auto-generated method stub
		int sum=0;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("Select * from "+ TABLE_DATA + " where username = ? and year=?",new String[] {string,Integer.toString(yy)});
		if (cursor.getCount()!=0)
		{
			cursor.moveToFirst();
			do
			{	
				sum+=Integer.parseInt(cursor.getString(2));
			}while(cursor.moveToNext());	
			return Integer.toString(sum);
		}
		else
			return "0";
	}

	public String rettotal(String string) {
		// TODO Auto-generated method stub
		int sum=0;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("Select * from "+ TABLE_DATA + " where username = ? and user=?",new String[] {string,"Self"});
		if (cursor.getCount()!=0)
		{
			cursor.moveToFirst();
			do
			{	
				sum+=Integer.parseInt(cursor.getString(2));
			}while(cursor.moveToNext());	
			return Integer.toString(sum);
		}
		else
			return "0";
	}

	public String retcredit(String string) {
		// TODO Auto-generated method stub
		int sum=0;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("Select * from "+ TABLE_DATA + " where username = ? ",new String[] {string});
		if (cursor.getCount()!=0)
		{
			cursor.moveToFirst();
			do
			{	
				sum+=Integer.parseInt(cursor.getString(2));
			}while(cursor.moveToNext());	
			return Integer.toString(sum);
		}
		else
			return "0";
	}

	public ArrayList<User> getNotUser(String name) {
		ArrayList<User> userList = new ArrayList<User>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor=db.rawQuery("SELECT * FROM "+ TABLE_DATA+ " WHERE username = ? and user != ? order by year desc,month desc,day desc", new String[]{name,"Self"});
		if (cursor.getCount()!=0) {
			cursor.moveToFirst();
			do {
				User user = new User();
				user.username =cursor.getString(0);
				user.title=cursor.getString(1);
				user.amount= cursor.getString(2);
				user.userpay = cursor.getString(3);
				user.day = cursor.getInt(4);
				user.month = cursor.getInt(5);
				user.year = cursor.getInt(6);
				user.notes=cursor.getString(7);
				userList.add(user);
			} while (cursor.moveToNext());
			
		}
		db.close();
		return userList;
	}
	
	*/
}
