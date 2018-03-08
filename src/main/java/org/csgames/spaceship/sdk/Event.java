package org.csgames.spaceship.sdk;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Event {

  public final EventType type;
  public final String target;
  public final String payload;

  Event(EventType type, String target, String payload) {
    this.type = type;
    this.target = target;
    this.payload = payload;
  }
}
