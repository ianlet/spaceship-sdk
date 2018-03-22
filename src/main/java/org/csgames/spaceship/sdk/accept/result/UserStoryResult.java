package org.csgames.spaceship.sdk.accept.result;

import java.time.Instant;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class UserStoryResult {

  public final UserStoryResultType type;
  public final String team;
  public final String name;
  public final int points;
  public final int penalties;
  public final double deaths;
  public final Instant timestamp;

  public UserStoryResult(UserStoryResultType type, String team, String name, int points, int penalties, double deaths, Instant timestamp) {
    this.type = type;
    this.team = team;
    this.name = name;
    this.points = points;
    this.penalties = penalties;
    this.deaths = deaths;
    this.timestamp = timestamp;
  }
}
