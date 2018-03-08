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

  private Headquarters headquarters;
  private SpaceshipService spaceshipService;
  private SpaceshipBlueprint theSpaceshipBlueprint;
  private EventFactory eventFactory;

  @Before
  public void setUp() throws Exception {
    theSpaceshipBlueprint = mock(SpaceshipBlueprint.class);
    headquarters = mock(Headquarters.class);
    eventFactory = new EventFactory();
    spaceshipService = new SpaceshipService(headquarters, theSpaceshipBlueprint, eventFactory);
  }

  @Test
  public void itShouldRecordThatFishWereSentToTarget() {
    spaceshipService.sendFishTo(THE_TARGET, SOME_FISH);

    verifyEventRecorded(EventType.FISH_SENT, THE_TARGET, SOME_FISH);
  }

  @Test
  public void itShouldRecordThatWaterWasSentToTarget() {
    spaceshipService.sendWaterTo(THE_TARGET, SOME_WATER_IN_LITER);

    verifyEventRecorded(EventType.WATER_SENT, THE_TARGET, SOME_WATER_IN_LITER);
  }

  @Test
  public void itShouldRecordThatTheDoorOfTheRoomWasOpen() {
    spaceshipService.openDoor(THE_ROOM, THE_DOOR_NUMBER);

    verifyEventRecorded(EventType.DOOR_OPEN, THE_ROOM, THE_DOOR_NUMBER);
  }

  @Test
  public void itShouldRecordThatTheDoorOfTheRoomWasClosed() {
    spaceshipService.closeDoor(THE_ROOM, THE_DOOR_NUMBER);

    verifyEventRecorded(EventType.DOOR_CLOSED, THE_ROOM, THE_DOOR_NUMBER);
  }

  @Test
  public void itShouldReadTheSpaceshipBlueprint() {
    SpaceshipBlueprint spaceshipBlueprint = spaceshipService.readBlueprint();

    assertThat(spaceshipBlueprint).isEqualTo(theSpaceshipBlueprint);
  }

  private void verifyEventRecorded(EventType eventType, String theTarget, int payload) {
    Event expectedEvent = eventFactory.create(eventType, theTarget, String.format("%d", payload));
    verify(headquarters).recordEvent(expectedEvent);
  }
}
