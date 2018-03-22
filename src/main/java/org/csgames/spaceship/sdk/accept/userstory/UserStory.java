package org.csgames.spaceship.sdk.accept.userstory;

import java.util.List;

public class UserStory {

  public final String name;
  public final List<Scenario> scenarios;
  public final int points;
  public final int penalties;
  public final double deaths;

  public UserStory(String name, List<Scenario> scenarios, int points, int penalties, double deaths) {
    this.name = name;
    this.scenarios = scenarios;
    this.points = points;
    this.penalties = penalties;
    this.deaths = deaths;
  }
}
