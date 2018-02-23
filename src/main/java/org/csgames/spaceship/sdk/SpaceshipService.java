package org.csgames.spaceship.sdk;

public class SpaceshipService {

  private final HeadquartersClient headquartersClient;

  public SpaceshipService(HeadquartersClient headquartersClient) {
    this.headquartersClient = headquartersClient;
  }

  public void sendFishTo(String target, int fishCount) {
  }

  public void sendWaterTo(String source, int waterInLiter) {
  }

  public void openDoor(Integer doorNumber) {
  }

  public void closeDoor(Integer doorNumber) {
  }

  public SpaceshipBlueprint readBlueprint() {
    return new SpaceshipBlueprint();
  }
}
