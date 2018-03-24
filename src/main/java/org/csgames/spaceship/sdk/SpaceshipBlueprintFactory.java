package org.csgames.spaceship.sdk;

import java.util.Arrays;

public class SpaceshipBlueprintFactory {

  public SpaceshipBlueprint generate() {
    SpaceshipRoom room0 = new SpaceshipRoom(0, "habitable");
    SpaceshipRoom room1 = new SpaceshipRoom(1, "habitable");
    SpaceshipRoom room2 = new SpaceshipRoom(2, "habitable");
    SpaceshipRoom room3 = new SpaceshipRoom(3, "freezer");
    SpaceshipRoom room4 = new SpaceshipRoom(4, "habitable");
    SpaceshipRoom room5 = new SpaceshipRoom(5, "habitable");
    SpaceshipRoom room6 = new SpaceshipRoom(6, "freezer");
    SpaceshipRoom room7 = new SpaceshipRoom(7, "habitable");
    SpaceshipRoom room8 = new SpaceshipRoom(8, "freezer");
    SpaceshipRoom room9 = new SpaceshipRoom(9, "freezer");

    return new SpaceshipBlueprint(Arrays.asList(room0, room1, room2, room3, room4, room5, room6, room7, room8, room9));
  }
}
