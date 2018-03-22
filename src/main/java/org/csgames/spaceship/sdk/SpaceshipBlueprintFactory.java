package org.csgames.spaceship.sdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SpaceshipBlueprintFactory {

  public static SpaceshipBlueprint generate() {
    List<Room> roomList = generateDefaultRoomList();
    return new SpaceshipBlueprint(roomList);
  }

  public static List<Room> generateDefaultRoomList() {
    Room room0 = new Room(0, "habitable");
    Room room1 = new Room(1, "habitable");
    Room room2 = new Room(2, "habitable");
    Room room3 = new Room(3, "freezer");
    Room room4 = new Room(4, "habitable");

    return new ArrayList<>(Arrays.asList(room0, room1, room2, room3, room4));
  }
}


