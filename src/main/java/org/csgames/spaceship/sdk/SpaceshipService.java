package org.csgames.spaceship.sdk;

import java.util.Random;

import static org.csgames.spaceship.sdk.EventType.AIR_CONDITIONING_CLOSED;
import static org.csgames.spaceship.sdk.EventType.AIR_CONDITIONING_OPEN;
import static org.csgames.spaceship.sdk.EventType.DOOR_CLOSED;
import static org.csgames.spaceship.sdk.EventType.DOOR_OPEN;
import static org.csgames.spaceship.sdk.EventType.FISH_SENT;
import static org.csgames.spaceship.sdk.EventType.MEAN_HABITABLE_TEMPERATURE_READ;
import static org.csgames.spaceship.sdk.EventType.ROOM_TEMPERATURE_READ;
import static org.csgames.spaceship.sdk.EventType.VENT_CLOSED;
import static org.csgames.spaceship.sdk.EventType.VENT_OPEN;
import static org.csgames.spaceship.sdk.EventType.WATER_SENT;

public class SpaceshipService {

  private final Headquarters headquarters;
  private final SpaceshipBlueprint spaceshipBlueprint;
  private final EventFactory eventFactory;

  public SpaceshipService(Headquarters headquarters, SpaceshipBlueprint spaceshipBlueprint, EventFactory eventFactory) {
    this.headquarters = headquarters;
    this.spaceshipBlueprint = spaceshipBlueprint;
    this.eventFactory = eventFactory;
  }

  public void sendFishTo(String target, int fishCount) {
    recordEvent(FISH_SENT, target, fishCount);
  }

  public void sendWaterTo(String target, double waterInLiter) {
    recordEvent(WATER_SENT, target, waterInLiter);
  }

  public void openDoor(int roomNumber) {
    recordEvent(DOOR_OPEN, roomNumber);
  }

  public void closeDoor(int roomNumber) {
    recordEvent(DOOR_CLOSED, roomNumber);
  }

  public void openVent(int roomNumber) {
    recordEvent(VENT_OPEN, roomNumber);
  }

  public void closeVent(int roomNumber) {
    recordEvent(VENT_CLOSED, roomNumber);
  }

  public void openAirConditioning(int roomNumber) {
    recordEvent(AIR_CONDITIONING_OPEN, roomNumber);
  }

  public void closeAirConditioning(int roomNumber) {
    recordEvent(AIR_CONDITIONING_CLOSED, roomNumber);
  }

  public double readRoomTemperature(int roomNumber) {
    recordEvent(ROOM_TEMPERATURE_READ, roomNumber);
    return new Random().nextDouble(); // FIXME: Read predefined room temperature
  }

  public double readMeanHabitableTemperature(int roomNumber) {
    recordEvent(MEAN_HABITABLE_TEMPERATURE_READ, roomNumber);
    return new Random().nextDouble(); // FIXME: Read predefined mean habitable temperature
  }

  public SpaceshipBlueprint readBlueprint() {
    return spaceshipBlueprint;
  }

  private void recordEvent(EventType eventType, String target, Object payload) {
    Event event = eventFactory.create(eventType, target, String.valueOf(payload));
    headquarters.recordEvent(event);
  }

  private void recordEvent(EventType eventType, int roomNumber) {
    Event event = eventFactory.create(eventType, String.valueOf(roomNumber));
    headquarters.recordEvent(event);
  }
}
