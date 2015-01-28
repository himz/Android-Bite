package com.sh.database;


import java.util.ArrayList;
import java.util.List;

import com.sh.entities.Restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Simple database access helper class. Defines the basic 
 * 
 * Inserting this file as template holder for our database for bite app. 
 */
public class LocalDbAdapter {

	/* Global Constants */
	private static final String TABLE_RESTAURANT_NAME ="Restaurant";
	private static final String TABLE_MENU_NAME = "Menu";

	private static final String CREATE_RESTAURANT_TABLE = "create table " + TABLE_RESTAURANT_NAME + "(restaurantID integer, restaurantName text)";
	/* Filter will be applied to each menu item. We need to provide the reason for that filter being applied here */
	private static final String CREATE_MENU_TABLE = "create table " + TABLE_MENU_NAME + "(menuID integer, menuName text, restaurantID integer, filterID integer, remarks text)";
	/* There should be a filter table separately, but for now, i have added filterID and remarks with MENU table. Lets Normalize the table later */

	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;
	private Context mCtx;

	/*Database creation sql statement*/


	private static final String TAG = "DBHelper";
	private static final String DATABASE_NAME = "biteDB";


	private static final int DATABASE_VERSION = 2;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		private Context context;

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			this.context = context;
			System.out.println("In DatabaseHelper constructor");
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			System.out.println("In onCreate()");
			db.execSQL(CREATE_RESTAURANT_TABLE);
			db.execSQL(CREATE_MENU_TABLE);
			/* Also seed data for default values */
			seedData(db);

		}

		public void seedData(SQLiteDatabase db){
			//@ToDo add Seed data here
			createRestaurantRow(db, 1, "Veggie Land");	
			createMenuRow(db, 1, "Pad Thai", 1, 1, "Vegan");
		}


		private long createRestaurantRow(SQLiteDatabase db, int restaurantID, String restaurantName) {
			ContentValues initialValues = new ContentValues();
			initialValues.put("restaurantID", restaurantID);
			initialValues.put("restaurantName", restaurantName);
			Log.d(TAG, "Inserting values in Restaurant Table");
			return db.insert(TABLE_RESTAURANT_NAME, null, initialValues);	
		}

		private long createMenuRow(SQLiteDatabase db, int menuID, String menuName, int restaurantID, int filterID, String remarks) {
			ContentValues initialValues = new ContentValues();
			initialValues.put("menuID", menuID);
			initialValues.put("menuName", menuName);
			initialValues.put("restaurantID", restaurantID);
			initialValues.put("filterID", filterID);
			initialValues.put("remarks", remarks);
			Log.d(TAG, "Inserting values in Menu Table");
			return db.insert(TABLE_MENU_NAME, null, initialValues);	
		}


		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS TripIt");
			onCreate(db);
		}
	}

	/**
	 * Constructor - takes the context to allow the database to be
	 * opened/created
	 * 
	 * @param ctx the Context within which to work
	 */
	public LocalDbAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	/**
	 * Open the bite database. If it cannot be opened, try to create a new
	 * instance of the database. If it cannot be created, throw an exception to
	 * signal the failure
	 * 
	 * @return this (self reference, allowing this to be chained in an
	 *         initialization call)
	 * @throws SQLException if the database could be neither opened or created
	 */
	public LocalDbAdapter open() throws SQLException {

		System.out.println("In open()");

		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();

		return this;
	}

	/**
	 * @brief Close the adapter
	 */
	public void close() {
		mDbHelper.close();
	}

	public List<String> getMenuForRestaurant(Integer restaurantID){
		Cursor c = null;
		List<String> menu = new ArrayList<String>();			
		c = mDb.rawQuery("select menuName from " + TABLE_MENU_NAME +" where restaurantID = " + "\"" + restaurantID +"\"", null);
		try{
			if (c.moveToFirst()) {
				do {
					menu.add(c.getString(0));
				} while (c.moveToNext());
			}
			c.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return menu ;
	}

	public Restaurant getRestaurantFromName(String restaurantName){
		Restaurant restaurant = new Restaurant();
		
		Cursor c = null;			
		c = mDb.rawQuery("select * from " + TABLE_RESTAURANT_NAME,null);// +" where restaurantName = " + "\"" + restaurantName +"\"", null);
		
		try{
			if (c.moveToFirst()) {
				do {
					/* Assuming only one restaurant of same name for now. Else only the last restaurant will be shown */
					restaurant.setName(c.getString(1));
					restaurant.setId(Integer.parseInt(c.getString(0)));
				} while (c.moveToNext());
			}
			c.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return restaurant ;
		
	}
	/**
	 * Remove all users and groups from database.
	 */
	public void deleteAllRowsFromTable(String tableName) {
		mDb.delete(tableName, null, null);
	}	

	/**
	 * Function to update the description of the activity
	 * @param description
	 * @param activityID
	 * @return true, if updated and false if not updated
	 */
	public boolean updateDescriptionOfActivity(String description, int activityID){
		String strSQL = "UPDATE ActivityTable SET description = " + "\"" + description +"\"" + "WHERE activityID = " + "\"" + activityID +"\"";
		if(strSQL != null){
			try {
				mDb.execSQL(strSQL);
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return false; 
	}

	/**
	 * Function to return the last activityID from the table
	 * @return 
	 */
	public int fetchRowCountOfTable(String tableName) {
		Cursor c = null;
		c = mDb.rawQuery("select count(*) from " + tableName, null);
		c.moveToFirst();
		int rowCount = c.getInt(0);
		return rowCount;
	}
} 