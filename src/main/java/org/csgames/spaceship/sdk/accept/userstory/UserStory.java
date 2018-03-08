package org.csgames.spaceship.sdk.accept.userstory;

import java.util.List;

public class UserStory {

  public final String name;
  public final List<Scenario> scenarios;

  public UserStory(String name, List<Scenario> scenarios) {
    this.name = name;
    this.scenarios = scenarios;
  }
}
