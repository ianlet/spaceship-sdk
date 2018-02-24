package org.csgames.spaceship.sdk;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommunicationServiceTest {

  private static final String THE_TARGET = "team-03";
  private static final String THE_SUPPLIER = "team-11";

  private static final int THE_DISTANCE = 3174;
  private static final Direction THE_DIRECTION = Direction.NORTH;
  private static final int THE_FISH_COUNT = 47;
  private static final int THE_WATER_AMOUNT = 15;

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

  @Test
  public void itShouldRecordThatTheTargetFetchedFishFromTheSupplier() {
    communicationService.fetchFishFrom(THE_TARGET, THE_SUPPLIER, THE_FISH_COUNT);

    String expectedFishFetched = String.format("%s,%d", THE_SUPPLIER, THE_FISH_COUNT);
    verifyEventRecorded(EventType.FISH_FETCHED, THE_TARGET, expectedFishFetched);
  }

  @Test
  public void itShouldRecordThatTheTargetFetchedWaterFromTheSupplier() {
    communicationService.fetchWaterFrom(THE_TARGET, THE_SUPPLIER, THE_WATER_AMOUNT);

    String expectedWaterFetched = String.format("%s,%d", THE_SUPPLIER, THE_WATER_AMOUNT);
    verifyEventRecorded(EventType.WATER_FETCHED, THE_TARGET, expectedWaterFetched);
  }

  private void verifyEventRecorded(EventType eventType, String target, String payload) {
    Event expectedEvent = new Event(eventType, target, payload);
    verify(headquarters).recordEvent(expectedEvent);
  }
}
