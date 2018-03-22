package org.csgames.spaceship.sdk;

import java.util.Arrays;

public class SpaceshipBlueprintFactory {

  public SpaceshipBlueprint generate() {
    SpaceshipRoom room0 = new SpaceshipRoom("room-00", "habitable");
    SpaceshipRoom room1 = new SpaceshipRoom("room-01", "habitable");
    SpaceshipRoom room2 = new SpaceshipRoom("room-02", "habitable");
    SpaceshipRoom room3 = new SpaceshipRoom("room-03", "freezer");
    SpaceshipRoom room4 = new SpaceshipRoom("room-04", "habitable");

    return new SpaceshipBlueprint(Arrays.asList(room0, room1, room2, room3, room4));
  }
}
