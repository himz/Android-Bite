package com.sh.database;

import java.util.ArrayList;
import java.util.List;

import com.sh.entities.Restaurant;
import com.sh.entities.RestaurantMenu;
import com.sh.helpers.App;

import android.app.Application;
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

    public static List<RestaurantMenu> getEveryOtherItemFromMenu(Context ctx) {
        List<RestaurantMenu> menuList = new ArrayList<RestaurantMenu>();
        RestaurantMenu menuItem = new RestaurantMenu();
        menuItem.setDishId(1);
        menuItem.setDishName("Huummus Beef");
        menuItem.setPrice(10.95);
        menuList.add(menuItem);

        return menuList;
    }

    public static List<RestaurantMenu> getOtherAlacarte(Context ctx) {
        List<RestaurantMenu> menuList = new ArrayList<RestaurantMenu>();
        RestaurantMenu menuItem = new RestaurantMenu();
        menuItem = new RestaurantMenu();
        menuItem.setDishId(1);
        menuItem.setDishName("Huummus Classic");
        menuItem.setPrice(8.95);
        menuList.add(menuItem);
        menuItem = new RestaurantMenu();
        menuItem.setDishId(1);
        menuItem.setDishName("Huummus Classic");
        menuItem.setPrice(8.95);
        menuList.add(menuItem);
        return menuList;
    }

    public static List<RestaurantMenu> getDietMenu(Context ctx) {
        List<RestaurantMenu> menuList = new ArrayList<RestaurantMenu>();
        RestaurantMenu menuItem = new RestaurantMenu();
        menuItem.setDishId(1);
        menuItem.setDishName("Huummus Classic");
        menuItem.setPrice(8.95);
        menuList.add(menuItem);
        menuItem = new RestaurantMenu();
        menuItem.setDishId(1);
        menuItem.setDishName("Huummus Chickpea");
        menuItem.setPrice(9.50);
        menuList.add(menuItem);
        menuItem = new RestaurantMenu();
        menuItem.setDishId(1);
        menuItem.setDishName("Huummus Fava");
        menuItem.setPrice(9.50);
        menuList.add(menuItem);
        menuItem = new RestaurantMenu();
        menuItem.setDishId(1);
        menuItem.setDishName("Huummus Mushrooms");
        menuItem.setPrice(9.95);
        menuList.add(menuItem);

        return menuList;
    }
}