package org.csgames.spaceship.sdk;

import static org.csgames.spaceship.sdk.EventType.AIR_CONDITIONING_CLOSED;
import static org.csgames.spaceship.sdk.EventType.AIR_CONDITIONING_OPEN;
import static org.csgames.spaceship.sdk.EventType.DOOR_CLOSED;
import static org.csgames.spaceship.sdk.EventType.DOOR_OPEN;
import static org.csgames.spaceship.sdk.EventType.FISH_SENT;
import static org.csgames.spaceship.sdk.EventType.VENT_CLOSED;
import static org.csgames.spaceship.sdk.EventType.VENT_OPEN;
import static org.csgames.spaceship.sdk.EventType.WATER_SENT;

public class SpaceshipService {

  private final Headquarters headquarters;
  private final SpaceshipBlueprintFactory spaceshipBlueprintFactory;
  private final EventFactory eventFactory;
  private final TemperatureReader temperatureReader;

  public SpaceshipService(Headquarters headquarters, SpaceshipBlueprintFactory spaceshipBlueprintFactory, EventFactory eventFactory, TemperatureReader temperatureReader) {
    this.headquarters = headquarters;
    this.spaceshipBlueprintFactory = spaceshipBlueprintFactory;
    this.eventFactory = eventFactory;
    this.temperatureReader = temperatureReader;
  }

  public void sendFishTo(String target, int fishCount) {
    recordEvent(FISH_SENT, target, fishCount);
  }

  public void sendWaterTo(String target, int waterInLiter) {
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

  public double readRoomTemperature(int roomNumber) throws TemperatureSensorNotWorkingException {
    return temperatureReader.readRoomTemperature(roomNumber);
  }

  public double readMeanHabitableTemperature() {
    return temperatureReader.readMeanHabitableTemperature();
  }

  public String roomTemperatureSensorUnit(int roomNumber) {
    switch (roomNumber) {
      case 0:
        return "F";
      case 2:
        return "F";
      case 7:
        return "F";
      default:
        return "C";
    }
  }

  public SpaceshipBlueprint readBlueprint() {
    return spaceshipBlueprintFactory.generate();
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
