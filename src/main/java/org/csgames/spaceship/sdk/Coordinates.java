package org.csgames.spaceship.sdk;

public class Coordinates {

  public final double latitude;
  public final double longitude;

  public Coordinates(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  @Override
  public String toString() {
    return String.format("%f,%f", latitude, longitude);
  }
}
