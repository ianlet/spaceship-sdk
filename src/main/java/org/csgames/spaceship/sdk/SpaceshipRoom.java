package org.csgames.spaceship.sdk;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class SpaceshipRoom {

  public final int roomNumber;
  public final String type;

  public SpaceshipRoom(int roomNumber, String type) {
    this.roomNumber = roomNumber;
    this.type = type;
  }
}
