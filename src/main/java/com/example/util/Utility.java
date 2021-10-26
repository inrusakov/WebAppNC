package com.example.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Utility {

    /**
     * Calculating distance between point using coords.
     * @param lat1 First point latitude.
     * @param lng1 First point longitude,
     * @param lat2 Second point latitude.
     * @param lng2 Second point longitude.
     * @return Distance between points.
     */
    public static double calculateDistance(double lat1, double lng1, double lat2, double lng2){
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);

        return dist;
    }

    /**
     * Выполняет проверку авторизованности пользователя
     * @return true - если авторизован, false - если не авторизован.
     */
    public static boolean isAuthenticated(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (principal instanceof UserDetails);
    }
}
