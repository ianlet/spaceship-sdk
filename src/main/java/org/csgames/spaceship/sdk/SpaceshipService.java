package org.csgames.spaceship.sdk;

public class SpaceshipService {

  private final HeadquartersClient headquartersClient;

  SpaceshipService(HeadquartersClient headquartersClient) {
    this.headquartersClient = headquartersClient;
  }

  public void sendFishTo(String target, int fishCount) {
    Command command = new Command(CommandType.SEND_FISH, target, String.format("%d", fishCount));
    headquartersClient.sendCommand(command);
  }

  public void sendWaterTo(String target, int waterInLiter) {
    Command command = new Command(CommandType.SEND_WATER, target, String.format("%d", waterInLiter));
    headquartersClient.sendCommand(command);
  }

  public void openDoor(Integer doorNumber) {
  }

  public void closeDoor(Integer doorNumber) {
  }

  public SpaceshipBlueprint readBlueprint() {
    return new SpaceshipBlueprint();
  }
}
