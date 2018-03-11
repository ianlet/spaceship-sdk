package org.csgames.spaceship.sdk.accept.userstory;

import org.csgames.spaceship.sdk.Event;

public class Result {

  public String name;
  public Event event;

  public Result(String name, Event event) {
    this.name = name;
    this.event = event;
  }

  public Result() {
  }
}
