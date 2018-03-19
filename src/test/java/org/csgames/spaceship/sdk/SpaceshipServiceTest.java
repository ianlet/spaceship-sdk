package org.csgames.spaceship.sdk;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SpaceshipServiceTest {

  private static final String THE_TARGET = "team-01";
  private static final int THE_ROOM_NUMBER = 21;

  private static final int SOME_FISH = 19;
  private static final double SOME_WATER_IN_LITER = 27.2;

  private Headquarters headquarters;
  private SpaceshipService spaceshipService;
  private SpaceshipBlueprint theSpaceshipBlueprint;
  private EventFactory eventFactory;

  @Before
  public void setUp() throws Exception {
    theSpaceshipBlueprint = mock(SpaceshipBlueprint.class);
    headquarters = mock(Headquarters.class);
    eventFactory = new EventFactory();
    RoomTemperatureReader roomTemperatureReader = mock(RoomTemperatureReader.class);

    spaceshipService = new SpaceshipService(headquarters, theSpaceshipBlueprint, eventFactory, roomTemperatureReader);
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

  @Test
  public void itShouldRecordThatTheDoorOfTheRoomWasOpen() {
    spaceshipService.openDoor(THE_ROOM_NUMBER);

    verifyEventRecorded(EventType.DOOR_OPEN, String.valueOf(THE_ROOM_NUMBER));
  }

  @Test
  public void itShouldRecordThatTheDoorOfTheRoomWasClosed() {
    spaceshipService.closeDoor(THE_ROOM_NUMBER);

    verifyEventRecorded(EventType.DOOR_CLOSED, String.valueOf(THE_ROOM_NUMBER));
  }

  @Test
  public void itShouldRecordThatTheVentWasOpen() {
    spaceshipService.openVent(THE_ROOM_NUMBER);

    verifyEventRecorded(EventType.VENT_OPEN, String.valueOf(THE_ROOM_NUMBER));
  }

  @Test
  public void itShouldRecordThatTheVentWasClosed() {
    spaceshipService.closeVent(THE_ROOM_NUMBER);

    verifyEventRecorded(EventType.VENT_CLOSED, String.valueOf(THE_ROOM_NUMBER));
  }

  @Test
  public void itShouldRecordThatTheAirConditiongWasOpen() {
    spaceshipService.openAirConditioning(THE_ROOM_NUMBER);

    verifyEventRecorded(EventType.AIR_CONDITIONING_OPEN, String.valueOf(THE_ROOM_NUMBER));
  }

  @Test
  public void itShouldRecordThatTheAirConditioningWasClosed() {
    spaceshipService.closeAirConditioning(THE_ROOM_NUMBER);

    verifyEventRecorded(EventType.AIR_CONDITIONING_CLOSED, String.valueOf(THE_ROOM_NUMBER));
  }

  @Test
  public void itShouldReadTheSpaceshipBlueprint() {
    SpaceshipBlueprint spaceshipBlueprint = spaceshipService.readBlueprint();

    assertThat(spaceshipBlueprint).isEqualTo(theSpaceshipBlueprint);
  }

  private void verifyEventRecorded(EventType eventType, String theTarget, String payload) {
    Event expectedEvent = eventFactory.create(eventType, theTarget, payload);
    verify(headquarters).recordEvent(expectedEvent);
  }

  private void verifyEventRecorded(EventType eventType, String target) {
    Event expectedEvent = eventFactory.create(eventType, target);
    verify(headquarters).recordEvent(expectedEvent);
  }
}
