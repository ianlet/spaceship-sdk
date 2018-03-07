package org.csgames.spaceship.sdk;

import java.time.Instant;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
class Event {

  public final EventType type;
  public final String target;
  public final String payload;
  public final Instant timestamp;

  Event(EventType type, String target, String payload, Instant timestamp) {
    this.type = type;
    this.target = target;
    this.payload = payload;
    this.timestamp = timestamp;
  }
}
