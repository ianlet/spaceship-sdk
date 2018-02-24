package org.csgames.spaceship.sdk;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SpaceshipServiceTest {

  private static final String THE_TARGET = "team-01";
  private static final int SOME_FISH = 19;

  @Test
  public void itShouldCommandToSendFishToTarget() {
    HeadquartersClient headquartersClient = mock(HeadquartersClient.class);
    SpaceshipService spaceshipService = new SpaceshipService(headquartersClient);

    spaceshipService.sendFishTo(THE_TARGET, SOME_FISH);

    Command expectedCommand = new Command(CommandType.SEND_FISH, THE_TARGET, String.format("%d", SOME_FISH));
    verify(headquartersClient).sendCommand(expectedCommand);
  }
}
