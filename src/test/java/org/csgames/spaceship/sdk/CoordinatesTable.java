package org.csgames.spaceship.sdk;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertWithMessage;

class CoordinatesTable {

  private List<Coordinates> from = new ArrayList<>();
  private List<Coordinates> to = new ArrayList<>();
  private List<Direction> expectedDirections = new ArrayList<>();

  public void push(Coordinates from, Coordinates to, Direction expectedDirection) {
    this.from.add(from);
    this.to.add(to);
    this.expectedDirections.add(expectedDirection);
  }

  public void forEach(CalculateDirection<Coordinates, Coordinates, Direction> fun) {
    for (int i = 0; i < expectedDirections.size(); i++) {
      Coordinates from = this.from.get(i);
      Coordinates to = this.to.get(i);

      Direction direction = fun.apply(from, to);

      Direction expectedDirection = expectedDirections.get(i);
      assertWithMessage(String.format("Compass point from %s to %s", from, to)).that(direction).isEqualTo(expectedDirection);
    }
  }

  @FunctionalInterface
  public interface CalculateDirection<T, T1, T2> {

    T2 apply(T t, T1 t1);
  }
}
