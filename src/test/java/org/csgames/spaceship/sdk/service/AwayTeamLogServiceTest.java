package org.csgames.spaceship.sdk.service;

import org.csgames.spaceship.sdk.Event;
import org.csgames.spaceship.sdk.EventFactory;
import org.csgames.spaceship.sdk.EventType;
import org.csgames.spaceship.sdk.Headquarters;
import org.junit.Before;
import org.junit.Test;

import static org.csgames.spaceship.sdk.EventType.MEASURED_DATA_REPORTED;
import static org.csgames.spaceship.sdk.EventType.TEAM_STATUS_REPORTED;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AwayTeamLogServiceTest {

  private static final String AWAY_TEAM = "away-team-07";
  private static final TeamStatus AWAY_TEAM_STATUS = TeamStatus.MOVING;
  private static final String MEASURED_DATA = "CO2";

  private Headquarters headquarters;
  private EventFactory eventFactory;

  private AwayTeamLogService awayTeamLogService;

  @Before
  public void setUp() throws Exception {
    eventFactory = new EventFactory();
    headquarters = mock(Headquarters.class);

    awayTeamLogService = new AwayTeamLogService(eventFactory, headquarters);
  }

  @Test
  public void itShouldRecordTheReportedStatus() {
    awayTeamLogService.reportStatus(AWAY_TEAM, AWAY_TEAM_STATUS);

    Event reportedStatusEvent = expectEvent(TEAM_STATUS_REPORTED, AWAY_TEAM, AWAY_TEAM_STATUS.toString());
    verify(headquarters).recordEvent(reportedStatusEvent);
  }

  @Test
  public void itShouldRecordTheReportedMeasuredData() {
    awayTeamLogService.reportMeasureData(AWAY_TEAM, MEASURED_DATA);

    Event reportedMeasuredData = expectEvent(MEASURED_DATA_REPORTED, AWAY_TEAM, MEASURED_DATA);
    verify(headquarters).recordEvent(reportedMeasuredData);
  }

  private Event expectEvent(EventType eventType, String target, String payload) {
    return eventFactory.create(eventType, target, payload);
  }
}
