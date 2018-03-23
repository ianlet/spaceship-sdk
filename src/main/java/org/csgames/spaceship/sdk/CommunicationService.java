package org.csgames.spaceship.sdk;

import static org.csgames.spaceship.sdk.EventType.MOVED_TO;

public class CommunicationService {

  private final Headquarters headquarters;
  private final EventFactory eventFactory;

  public CommunicationService(Headquarters headquarters, EventFactory eventFactory) {
    this.headquarters = headquarters;
    this.eventFactory = eventFactory;
  }

  public void moveTo(String target, Direction direction, int distance) {
    String movement = String.format("%s,%d", direction, distance);
    recordEvent(MOVED_TO, target, movement);
  }

  private void recordEvent(EventType eventType, String target, String payload) {
    Event event = eventFactory.create(eventType, target, payload);
    headquarters.recordEvent(event);
  }
}
