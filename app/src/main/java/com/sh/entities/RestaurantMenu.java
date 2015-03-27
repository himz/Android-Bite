package com.sh.entities;

/**
 * Created by himanshu on 3/26/15.
 */
public class RestaurantMenu {
    String dishName;
    String dishIngredients;
    String dishTags;
    int dishId;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    double price;

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishIngredients() {
        return dishIngredients;
    }

    public void setDishIngredients(String dishIngredients) {
        this.dishIngredients = dishIngredients;
    }

    public String getDishTags() {
        return dishTags;
    }

    public void setDishTags(String dishTags) {
        this.dishTags = dishTags;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    int restaurantId;
}
