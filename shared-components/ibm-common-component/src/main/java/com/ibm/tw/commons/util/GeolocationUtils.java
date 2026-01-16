/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.ibm.tw.commons.util;

public class GeolocationUtils {

    public static final String KILOMETERS = "K";
    public static final String STATUS_MILES = "M";
    public static final String NAUTICAL_MILES = "N";

    public static double calcDistance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        if (KILOMETERS.equals(unit)) {
            dist = dist * 1.609344;
        }
        else if (NAUTICAL_MILES.equals(unit)) {
            dist = dist * 0.8684;
        }

        return dist;
    }

}
