package org.csgames.spaceship.sdk;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class CommunicationServiceTest {

  private static final String THE_TARGET = "team-03";
  private static final double THE_DISTANCE = 31D;

  @Test
  public void itShouldRecordThatTheTargetHasMoved() {
    Headquarters headquarters = mock(Headquarters.class);
    CommunicationService communicationService = new CommunicationService(headquarters);

    communicationService.moveTo(THE_TARGET, Direction.NORTH, THE_DISTANCE);
  }
}
