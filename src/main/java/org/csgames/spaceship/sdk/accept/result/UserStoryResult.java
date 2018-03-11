package org.csgames.spaceship.sdk.accept.result;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class UserStoryResult {

  public final UserStoryResultType type;
  public final String team;
  public final String name;

  public UserStoryResult(UserStoryResultType type, String team, String name) {
    this.type = type;
    this.team = team;
    this.name = name;
  }
}
