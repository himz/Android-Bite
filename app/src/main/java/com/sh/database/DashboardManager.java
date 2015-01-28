package com.sh.database;

import java.util.List;

import com.sh.entities.Restaurant;
import com.sh.helpers.App;

import android.content.Context;

/**
 * Example file to take care of calling the database
 *
 */
public class DashboardManager {
	static App app;
	
	public static List<String> getAllMenuForRestaurant(Context ctx, int restaurantID) {
		app = ((App)ctx.getApplicationContext());
		return app.db.getMenuForRestaurant(restaurantID);
	}

	public static Restaurant getRestaurantFromName(Context ctx, String restaurantName) {
		app = ((App)ctx.getApplicationContext());
		return app.db.getRestaurantFromName(restaurantName);
	}
}