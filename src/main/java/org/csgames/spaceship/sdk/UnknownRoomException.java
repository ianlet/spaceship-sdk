package org.csgames.spaceship.sdk;

public class UnknownRoomException extends RuntimeException {

  public UnknownRoomException(int roomNumber) {
    super(String.format("Holy penguin! How did you get that room number? There is no room %d on this spaceship!", roomNumber));
  }
}
