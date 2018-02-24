package org.csgames.spaceship.sdk;

import static org.csgames.spaceship.sdk.EventType.DOOR_CLOSED;
import static org.csgames.spaceship.sdk.EventType.DOOR_OPEN;
import static org.csgames.spaceship.sdk.EventType.FISH_SENT;
import static org.csgames.spaceship.sdk.EventType.WATER_SENT;

public class SpaceshipService {

  private final Headquarters headquarters;
  private final SpaceshipBlueprint spaceshipBlueprint;

  SpaceshipService(Headquarters headquarters, SpaceshipBlueprint spaceshipBlueprint) {
    this.headquarters = headquarters;
    this.spaceshipBlueprint = spaceshipBlueprint;
  }

  public void sendFishTo(String target, int fishCount) {
    recordEvent(FISH_SENT, target, fishCount);
  }

  public void sendWaterTo(String target, int waterInLiter) {
    recordEvent(WATER_SENT, target, waterInLiter);
  }

  public void openDoor(String target, int doorNumber) {
    recordEvent(DOOR_OPEN, target, doorNumber);
  }

  public void closeDoor(String target, int doorNumber) {
    recordEvent(DOOR_CLOSED, target, doorNumber);
  }

  public SpaceshipBlueprint readBlueprint() {
    return spaceshipBlueprint;
  }

  private void recordEvent(EventType eventType, String target, Object payload) {
    Event event = new Event(eventType, target, String.format("%s", payload));
    headquarters.recordEvent(event);
  }
}
