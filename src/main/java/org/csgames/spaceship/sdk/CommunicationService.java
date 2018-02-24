package org.csgames.spaceship.sdk;

public class CommunicationService {

  private final Headquarters headquarters;

  CommunicationService(Headquarters headquarters) {
    this.headquarters = headquarters;
  }

  public void moveTo(String target, Direction direction, double distance) {
  }

  public void fetchFishFrom(String consumer, String supplier) {
  }

  public void fetchWaterFrom(String consumer, String supplier) {
  }

  public void catchFish(String target, double longitude, double latitude) {
  }

  public void refillWater(String target, double longitude, double latitude) {
  }
}
