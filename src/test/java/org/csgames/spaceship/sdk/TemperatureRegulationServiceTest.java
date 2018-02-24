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

    verify(headquarters).recordEvent(new Event(EventType.AIR_VENT_OPEN, THE_ROOM));
  }

  @Test
  public void itShouldRecordThatTheRoomAirVentWasClosed() {
    temperatureRegulationService.closeAirVent(THE_ROOM);

    verify(headquarters).recordEvent(new Event(EventType.AIR_VENT_CLOSED, THE_ROOM));
  }
}
