package org.csgames.spaceship.sdk;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
class Event {

  private final EventType type;
  private final String target;
  private final String payload;

  Event(EventType type, String target, String payload) {
    this.type = type;
    this.target = target;
    this.payload = payload;
  }

  Event(EventType type, String target) {
    this.type = type;
    this.target = target;
    this.payload = "";
  }
}
