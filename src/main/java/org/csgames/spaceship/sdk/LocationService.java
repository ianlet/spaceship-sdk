package org.csgames.spaceship.sdk;

import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static org.csgames.spaceship.sdk.Direction.EAST;
import static org.csgames.spaceship.sdk.Direction.NORTH;
import static org.csgames.spaceship.sdk.Direction.NORTH_EAST;
import static org.csgames.spaceship.sdk.Direction.NORTH_WEST;
import static org.csgames.spaceship.sdk.Direction.SOUTH;
import static org.csgames.spaceship.sdk.Direction.SOUTH_EAST;
import static org.csgames.spaceship.sdk.Direction.SOUTH_WEST;
import static org.csgames.spaceship.sdk.Direction.WEST;

public class LocationService {

  private static final double EARTH_RADIUS = 6378.1370D;
  private static final double DEG_TO_RAD = (PI / 180D);
  private static final double RAD_TO_DEG = (180D / PI);

  public int distanceBetween(double lat1, double long1, double lat2, double long2) {
    return (int) (1000D * haversineInKm(lat1, long1, lat2, long2));
  }

  private double haversineInKm(double lat1, double long1, double lat2, double long2) {
    double dLong = (long2 - long1) * DEG_TO_RAD;
    double dLat = (lat2 - lat1) * DEG_TO_RAD;

    double a = pow(sin(dLat / 2D), 2D) + (cos(lat1 * DEG_TO_RAD) * cos(lat2 * DEG_TO_RAD)
      * pow(sin(dLong / 2D), 2D));
    double c = 2D * atan2(sqrt(a), sqrt(1D - a));

    return EARTH_RADIUS * c;
  }

  public Direction directionTo(double lat1, double long1, double lat2, double long2) {
    double bearing = calculateBearing(lat1, long1, lat2, long2);

    if (bearing >= -22.50 && bearing <= 22.50) {
      return NORTH;
    }
    if (bearing >= 22.50 && bearing <= 67.50) {
      return NORTH_EAST;
    }
    if (bearing >= 67.50 && bearing <= 112.50) {
      return EAST;
    }
    if (bearing >= 112.50 && bearing <= 157.50) {
      return SOUTH_EAST;
    }
    if (bearing >= 157.50 || bearing <= -157.5) {
      return SOUTH;
    }
    if (bearing >= -157.50 && bearing <= -112.50) {
      return SOUTH_WEST;
    }
    if (bearing >= -112.50 && bearing <= -67.50) {
      return WEST;
    }
    if (bearing >= -67.50 && bearing <= -22.50) {
      return NORTH_WEST;
    }
    return Direction.NONE;
  }

  private double calculateBearing(double lat1, double long1, double lat2, double long2) {
    double dLong = (long2 - long1) * DEG_TO_RAD;
    lat1 *= DEG_TO_RAD;
    lat2 *= DEG_TO_RAD;
    double a = sin(dLong) * cos(lat2);
    double b = (cos(lat1) * sin(lat2)) - (sin(lat1) * cos(lat2) * cos(dLong));
    return atan2(a, b) * RAD_TO_DEG;
  }
}
