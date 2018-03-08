package org.csgames.spaceship.sdk.accept.userstory;

import org.csgames.spaceship.sdk.Event;

public class Result {

  private String result;
  public Event event;

  public Result(String result, Event event) {
    this.result = result;
    this.event = event;
  }

  public Result() {
  }
}
