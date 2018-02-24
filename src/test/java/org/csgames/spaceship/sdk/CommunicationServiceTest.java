package org.csgames.spaceship.sdk;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommunicationServiceTest {

  private static final String THE_TARGET = "team-03";
  private static final int THE_DISTANCE = 3174;
  private static final Direction THE_DIRECTION = Direction.NORTH;
  private Headquarters headquarters;
  private CommunicationService communicationService;

  @Before
  public void setUp() throws Exception {
    headquarters = mock(Headquarters.class);
    communicationService = new CommunicationService(headquarters);
  }

  @Test
  public void itShouldRecordThatTheTargetHasMovedToTheDirectionAndDistance() {
    communicationService.moveTo(THE_TARGET, Direction.NORTH, THE_DISTANCE);

    String expectedMovement = String.format("%s,%d", THE_DIRECTION, THE_DISTANCE);
    verifyEventRecorded(EventType.MOVED_TO, THE_TARGET, expectedMovement);
  }

  private void verifyEventRecorded(EventType eventType, String target, String payload) {
    Event expectedEvent = new Event(eventType, target, payload);
    verify(headquarters).recordEvent(expectedEvent);
  }
}
