package org.csgames.spaceship.sdk;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class SpaceshipRoom {

  public final String roomNumber;
  public final String type;

  public SpaceshipRoom(String roomNumber, String type) {
    this.roomNumber = roomNumber;
    this.type = type;
  }
}
