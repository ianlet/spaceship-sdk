package org.csgames.spaceship.sdk;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertWithMessage;

class CoordinatesTable {

  private List<Coordinates> from = new ArrayList<>();
  private List<Coordinates> to = new ArrayList<>();
  private List<String> expectedDirections = new ArrayList<>();

  public void push(Coordinates from, Coordinates to, String expectedDirection) {
    this.from.add(from);
    this.to.add(to);
    this.expectedDirections.add(expectedDirection);
  }

  public void forEach(CalculateDirection<Coordinates, Coordinates, String> fun) {
    for (int i = 0; i < expectedDirections.size(); i++) {
      Coordinates from = this.from.get(i);
      Coordinates to = this.to.get(i);

      String direction = fun.apply(from, to);

      String expectedDirection = expectedDirections.get(i);
      assertWithMessage(String.format("Compass point from %s to %s", from, to)).that(direction).isEqualTo(expectedDirection);
    }
  }

  public static class Coordinates {

    public final double latitude;
    public final double longitude;

    public Coordinates(double latitude, double longitude) {
      this.latitude = latitude;
      this.longitude = longitude;
    }

    @Override
    public String toString() {
      return String.format("(%f,%f)", latitude, longitude);
    }
  }

  @FunctionalInterface
  public interface CalculateDirection<T, T1, T2> {

    T2 apply(T t, T1 t1);
  }
}
