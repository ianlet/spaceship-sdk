package org.csgames.spaceship.sdk;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TemperatureRegulationServiceTest {

  private static final String THE_ROOM = "room-01";

  private Headquarters headquarters;
  private TemperatureRegulationService temperatureRegulationService;

  @Before
  public void setUp() throws Exception {
    headquarters = mock(Headquarters.class);
    temperatureRegulationService = new TemperatureRegulationService(headquarters);
  }

  @Test
  public void itShouldRecordThatTheRoomAirVentWasOpen() {
    temperatureRegulationService.openAirVent(THE_ROOM);

    verifyEventRecorded(EventType.AIR_VENT_OPEN, THE_ROOM);
  }

  @Test
  public void itShouldRecordThatTheRoomAirVentWasClosed() {
    temperatureRegulationService.closeAirVent(THE_ROOM);

    verifyEventRecorded(EventType.AIR_VENT_CLOSED, THE_ROOM);
  }

  @Test
  public void itShouldRecordThatTheRoomAirConditioningWasStarted() {
    temperatureRegulationService.startAirConditioning(THE_ROOM);

    verifyEventRecorded(EventType.AIR_CONDITIONING_STARTED, THE_ROOM);
  }

  @Test
  public void itShouldRecordThatTheRoomAirConditioningWasStopped() {
    temperatureRegulationService.stopAirConditioning(THE_ROOM);

    verifyEventRecorded(EventType.AIR_CONDITIONING_STOPPED, THE_ROOM);
  }

  private void verifyEventRecorded(EventType eventType, String theRoom) {
    Event expectedEvent = new Event(eventType, theRoom);
    verify(headquarters).recordEvent(expectedEvent);
  }
}
