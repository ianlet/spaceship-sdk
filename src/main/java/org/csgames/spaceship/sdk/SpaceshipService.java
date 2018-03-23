package org.csgames.spaceship.sdk;

import static org.csgames.spaceship.sdk.EventType.FISH_SENT;
import static org.csgames.spaceship.sdk.EventType.WATER_SENT;

public class SpaceshipService {

  private final Headquarters headquarters;
  private final EventFactory eventFactory;

  public SpaceshipService(Headquarters headquarters, EventFactory eventFactory) {
    this.headquarters = headquarters;
    this.eventFactory = eventFactory;
  }

  public void sendFishTo(String target, int fishCount) {
    recordEvent(FISH_SENT, target, fishCount);
  }

  public void sendWaterTo(String target, int waterInLiter) {
    recordEvent(WATER_SENT, target, waterInLiter);
  }

  private void recordEvent(EventType eventType, String target, Object payload) {
    Event event = eventFactory.create(eventType, target, String.valueOf(payload));
    headquarters.recordEvent(event);
  }
}
