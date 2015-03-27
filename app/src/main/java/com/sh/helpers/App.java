package com.sh.helpers;

/**
 * Class to store all the global variables.
 * Mainly handling database connections for now
 * As a general practice, start moving shared state among actiities to this
 * class.
 *
 * @author himanshu
 *
 */
import com.sh.database.*;
import com.sh.entities.RestaurantMenu;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {
	/* List of global variables, to be shared across activities */
	public LocalDbAdapter db;
    public String selectedRestaurantName = "Oren's Hummus";
    public List<RestaurantMenu> menuList = new ArrayList<RestaurantMenu>();;


	@Override
	public void onCreate() {
		super.onCreate();
		Context ctx = getApplicationContext();
        selectedRestaurantName = new String();
        selectedRestaurantName = "Oren's Hummus";
        menuList = new ArrayList<RestaurantMenu>();
		this.db = new LocalDbAdapter(ctx);
		this.db.open();
	}
	@Override
	public void onTerminate() {
		this.db.close();
		super.onTerminate();
	}
}