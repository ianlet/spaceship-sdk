package org.csgames.spaceship.sdk;

public class Room {
  public final int roomNumber;
  public final String type;

  public Room(int roomNumber, String type) {
    this.roomNumber = roomNumber;
    this.type = type;
  }

  @Override
  public String toString() {
    return String.format("%f,%f", roomNumber, type);
  }
}
