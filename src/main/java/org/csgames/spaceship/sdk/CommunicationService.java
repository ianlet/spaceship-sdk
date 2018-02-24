package org.csgames.spaceship.sdk;

import static org.csgames.spaceship.sdk.EventType.FISH_FETCHED;
import static org.csgames.spaceship.sdk.EventType.MOVED_TO;
import static org.csgames.spaceship.sdk.EventType.WATER_FETCHED;

public class CommunicationService {

  private final Headquarters headquarters;

  CommunicationService(Headquarters headquarters) {
    this.headquarters = headquarters;
  }

  public void moveTo(String target, Direction direction, int distance) {
    String movement = String.format("%s,%d", direction, distance);
    recordEvent(MOVED_TO, target, movement);
  }

  public void fetchFishFrom(String consumer, String supplier, int fishCount) {
    String fishFetchedFrom = String.format("%s,%d", supplier, fishCount);
    recordEvent(FISH_FETCHED, consumer, fishFetchedFrom);
  }

  public void fetchWaterFrom(String consumer, String supplier, int waterInLiter) {
    String waterFetchedFrom = String.format("%s,%d", supplier, waterInLiter);
    recordEvent(WATER_FETCHED, consumer, waterFetchedFrom);
  }

  public void catchFish(String target, double longitude, double latitude) {
  }

  public void refillWater(String target, double longitude, double latitude) {
  }

  private void recordEvent(EventType eventType, String target, String payload) {
    Event event = new Event(eventType, target, payload);
    headquarters.recordEvent(event);
  }
}
