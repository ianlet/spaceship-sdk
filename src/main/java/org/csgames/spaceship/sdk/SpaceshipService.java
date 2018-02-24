package org.csgames.spaceship.sdk;

import static org.csgames.spaceship.sdk.CommandType.CLOSE_DOOR;
import static org.csgames.spaceship.sdk.CommandType.OPEN_DOOR;
import static org.csgames.spaceship.sdk.CommandType.SEND_FISH;
import static org.csgames.spaceship.sdk.CommandType.SEND_WATER;

public class SpaceshipService {

  private final HeadquartersClient headquartersClient;

  SpaceshipService(HeadquartersClient headquartersClient) {
    this.headquartersClient = headquartersClient;
  }

  public void sendFishTo(String target, int fishCount) {
    Command command = new Command(SEND_FISH, target, String.format("%d", fishCount));
    headquartersClient.sendCommand(command);
  }

  public void sendWaterTo(String target, int waterInLiter) {
    Command command = new Command(SEND_WATER, target, String.format("%d", waterInLiter));
    headquartersClient.sendCommand(command);
  }

  public void openDoor(String target, int doorNumber) {
    Command command = new Command(OPEN_DOOR, target, String.format("%d", doorNumber));
    headquartersClient.sendCommand(command);
  }

  public void closeDoor(String target, int doorNumber) {
    Command command = new Command(CLOSE_DOOR, target, String.format("%d", doorNumber));
    headquartersClient.sendCommand(command);
  }

  public SpaceshipBlueprint readBlueprint() {
    return new SpaceshipBlueprint();
  }
}
