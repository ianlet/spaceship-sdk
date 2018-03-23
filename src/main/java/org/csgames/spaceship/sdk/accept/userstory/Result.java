package org.csgames.spaceship.sdk.accept.userstory;

import org.csgames.spaceship.sdk.Event;

public class Result {

  public String name;
  public Event event;
  public boolean never;

  public Result(String name, boolean never, Event event) {
    this.name = name;
    this.never = never;
    this.event = event;
  }

  public Result() {
  }
}
