package org.csgames.spaceship.sdk.service;

import org.csgames.spaceship.sdk.Event;
import org.csgames.spaceship.sdk.EventFactory;
import org.csgames.spaceship.sdk.EventType;
import org.csgames.spaceship.sdk.Headquarters;

import static org.csgames.spaceship.sdk.EventType.MEASURED_DATA_REPORTED;
import static org.csgames.spaceship.sdk.EventType.TEAM_STATUS_REPORTED;

public class AwayTeamLogService {

  private final EventFactory eventFactory;
  private final Headquarters headquarters;

  public AwayTeamLogService(EventFactory eventFactory, Headquarters headquarters) {
    this.eventFactory = eventFactory;
    this.headquarters = headquarters;
  }

  public void reportStatus(String awayTeam, TeamStatus teamStatus) {
    recordEvent(TEAM_STATUS_REPORTED, awayTeam, teamStatus.toString());
  }

  public void reportMeasureData(String awayTeam, String measuredData) {
    recordEvent(MEASURED_DATA_REPORTED, awayTeam, measuredData);
  }

  private void recordEvent(EventType type, String target, String payload) {
    Event event = eventFactory.create(type, target, payload);
    headquarters.recordEvent(event);
  }
}
