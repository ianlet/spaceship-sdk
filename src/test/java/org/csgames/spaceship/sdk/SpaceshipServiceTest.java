package org.csgames.spaceship.sdk;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SpaceshipServiceTest {

  private static final String THE_TARGET = "team-01";

  private static final int SOME_FISH = 19;
  private static final int SOME_WATER_IN_LITER = 27;

  private Headquarters headquarters;
  private EventFactory eventFactory;

  private SpaceshipService spaceshipService;

  @Before
  public void setUp() throws Exception {
    headquarters = mock(Headquarters.class);
    eventFactory = new EventFactory();

    spaceshipService = new SpaceshipService(headquarters, eventFactory);
  }

  @Test
  public void itShouldRecordThatFishWereSentToTarget() {
    spaceshipService.sendFishTo(THE_TARGET, SOME_FISH);

    verifyEventRecorded(EventType.FISH_SENT, THE_TARGET, String.valueOf(SOME_FISH));
  }

  @Test
  public void itShouldRecordThatWaterWasSentToTarget() {
    spaceshipService.sendWaterTo(THE_TARGET, SOME_WATER_IN_LITER);

    verifyEventRecorded(EventType.WATER_SENT, THE_TARGET, String.valueOf(SOME_WATER_IN_LITER));
  }

  private void verifyEventRecorded(EventType eventType, String theTarget, String payload) {
    Event expectedEvent = eventFactory.create(eventType, theTarget, payload);
    verify(headquarters).recordEvent(expectedEvent);
  }
}
