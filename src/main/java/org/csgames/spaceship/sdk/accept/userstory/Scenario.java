package org.csgames.spaceship.sdk.accept.userstory;

import java.util.List;

public class Scenario {

  public final String name;
  public final List<InputEvent> events;
  public final List<Result> results;

  public Scenario(String name, List<InputEvent> events, List<Result> results) {
    this.name = name;
    this.events = events;
    this.results = results;
  }
}
