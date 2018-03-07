package org.csgames.spaceship.sdk;

import java.time.Clock;

public class EventFactory {

  private final Clock clock;

  public EventFactory(Clock clock) {
    this.clock = clock;
  }

  public Event create(EventType eventType, String target, String payload) {
    return new Event(eventType, target, payload, clock.instant());
  }

  public Event create(EventType eventType, String target) {
    return create(eventType, target, "");
  }
}
