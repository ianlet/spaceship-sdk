package org.csgames.spaceship.sdk.accept.result;

import java.time.Clock;

public class UserStoryResultFactory {

  private final Clock clock;

  public UserStoryResultFactory(Clock clock) {
    this.clock = clock;
  }

  public UserStoryResult create(UserStoryResultType type, String teamToken, String name) {
    return new UserStoryResult(type, teamToken, name);
  }
}
