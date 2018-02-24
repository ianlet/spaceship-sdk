package org.csgames.spaceship.sdk;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SpaceshipServiceTest {

  private static final String THE_TARGET = "team-01";
  private static final String THE_ROOM = "room-21";
  private static final int THE_DOOR_NUMBER = 3;

  private static final int SOME_FISH = 19;
  private static final int SOME_WATER_IN_LITER = 27;

  private HeadquartersClient headquartersClient;
  private SpaceshipService spaceshipService;
  private SpaceshipBlueprint theSpaceshipBlueprint;

  @Before
  public void setUp() throws Exception {
    theSpaceshipBlueprint = mock(SpaceshipBlueprint.class);
    headquartersClient = mock(HeadquartersClient.class);
    spaceshipService = new SpaceshipService(headquartersClient, theSpaceshipBlueprint);
  }

  @Test
  public void itShouldCommandToSendFishToTarget() {
    spaceshipService.sendFishTo(THE_TARGET, SOME_FISH);

    verifyCommandSent(CommandType.SEND_FISH, THE_TARGET, SOME_FISH);
  }

  @Test
  public void itShouldCommandToSendWaterToTarget() {
    spaceshipService.sendWaterTo(THE_TARGET, SOME_WATER_IN_LITER);

    verifyCommandSent(CommandType.SEND_WATER, THE_TARGET, SOME_WATER_IN_LITER);
  }

  @Test
  public void itShouldOpenTheDoorOfTheTarget() {
    spaceshipService.openDoor(THE_ROOM, THE_DOOR_NUMBER);

    verifyCommandSent(CommandType.OPEN_DOOR, THE_ROOM, THE_DOOR_NUMBER);
  }

  @Test
  public void itShouldCloseTheDoorOfTheTarget() {
    spaceshipService.closeDoor(THE_ROOM, THE_DOOR_NUMBER);

    verifyCommandSent(CommandType.CLOSE_DOOR, THE_ROOM, THE_DOOR_NUMBER);
  }

  @Test
  public void itShouldReadTheSpaceshipBlueprint() {
    SpaceshipBlueprint spaceshipBlueprint = spaceshipService.readBlueprint();

    assertThat(spaceshipBlueprint).isEqualTo(theSpaceshipBlueprint);
  }

  private void verifyCommandSent(CommandType commandType, String theTarget, int someFish) {
    Command expectedCommand = new Command(commandType, theTarget, String.format("%d", someFish));
    verify(headquartersClient).sendCommand(expectedCommand);
  }
}
