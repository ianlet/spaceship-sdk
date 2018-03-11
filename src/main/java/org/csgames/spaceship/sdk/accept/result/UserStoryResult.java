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
  public final Instant timestamp;

  public UserStoryResult(UserStoryResultType type, String team, String name, Instant timestamp) {
    this.type = type;
    this.team = team;
    this.name = name;
    this.timestamp = timestamp;
  }
}
