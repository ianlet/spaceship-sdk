package org.csgames.spaceship.sdk;

public class EventFactory {

  public EventFactory() {
  }

  public Event create(EventType eventType, String target, String payload) {
    return new Event(eventType, target, payload);
  }

  public Event create(EventType eventType, String target) {
    return create(eventType, target, "");
  }
}
