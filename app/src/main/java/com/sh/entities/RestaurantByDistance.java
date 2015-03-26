package com.sh.entities;

/**
 * Created by himanshu on 3/25/15.
 */
public class RestaurantByDistance extends Restaurant {
    public double getDistanceFromCurrentLocation() {
        return distanceFromCurrentLocation;
    }

    public void setDistanceFromCurrentLocation(double distanceFromCurrentLocation) {
        this.distanceFromCurrentLocation = distanceFromCurrentLocation;
    }

    /* Store restaurant distance from current location */
    double distanceFromCurrentLocation;
}
