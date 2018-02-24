package org.csgames.spaceship.sdk;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SpaceshipServiceTest {

  private static final String THE_TARGET = "team-01";

  private static final int SOME_FISH = 19;
  private static final int SOME_WATER_IN_LITER = 27;

  private HeadquartersClient headquartersClient;
  private SpaceshipService spaceshipService;

  @Before
  public void setUp() throws Exception {
    headquartersClient = mock(HeadquartersClient.class);
    spaceshipService = new SpaceshipService(headquartersClient);
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

  private void verifyCommandSent(CommandType commandType, String theTarget, int someFish) {
    Command expectedCommand = new Command(commandType, theTarget, String.format("%d", someFish));
    verify(headquartersClient).sendCommand(expectedCommand);
  }
}
