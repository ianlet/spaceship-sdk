package org.csgames.spaceship.sdk.service;

import org.csgames.spaceship.sdk.Event;
import org.csgames.spaceship.sdk.EventFactory;
import org.csgames.spaceship.sdk.Headquarters;

import static org.csgames.spaceship.sdk.EventType.TEAM_STATUS_REPORTED;

public class TeamPositioningService {

  private final EventFactory eventFactory;
  private final Headquarters headquarters;

  public TeamPositioningService(EventFactory eventFactory, Headquarters headquarters) {
    this.eventFactory = eventFactory;
    this.headquarters = headquarters;
  }

  public void reportStatus(String awayTeam, TeamStatus teamStatus) {
    Event event = eventFactory.create(TEAM_STATUS_REPORTED, awayTeam, teamStatus.toString());
    headquarters.recordEvent(event);
  }
}
