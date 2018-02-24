package org.csgames.spaceship.sdk;

public class LocationService {

  private static final double EARTH_RADIUS = 6378.1370D;
  private static final double DEG_TO_RAD = (Math.PI / 180D);

  public int distanceBetween(double lat1, double long1, double lat2, double long2) {
    return (int) (1000D * haversineInKm(lat1, long1, lat2, long2));
  }

  public String directionTo(double fromLat, double fromLong, double toLat, double toLong) {
    return "N";
  }

  private double haversineInKm(double lat1, double long1, double lat2, double long2) {
    double dlong = (long2 - long1) * DEG_TO_RAD;
    double dlat = (lat2 - lat1) * DEG_TO_RAD;

    double a = Math.pow(Math.sin(dlat / 2D), 2D) + Math.cos(lat1 * DEG_TO_RAD) * Math.cos(lat2 * DEG_TO_RAD)
      * Math.pow(Math.sin(dlong / 2D), 2D);
    double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));

    return EARTH_RADIUS * c;
  }
}
