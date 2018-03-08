package org.csgames.spaceship.sdk.accept.userstory;

public class InputEvent {

  public String name;
  public String type;
  public String source;
  public String payload;

  public InputEvent(String name, String type, String source, String payload) {
    this.name = name;
    this.type = type;
    this.source = source;
    this.payload = payload;
  }

  public InputEvent() {
  }
}
