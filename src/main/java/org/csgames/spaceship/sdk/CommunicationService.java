package org.csgames.spaceship.sdk;

public class CommunicationService {

  private final Headquarters headquarters;

  public CommunicationService(Headquarters headquarters) {
    this.headquarters = headquarters;
  }

  public void moveTo(String target, String direction, double distance) {
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
