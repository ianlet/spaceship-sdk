package org.csgames.spaceship.sdk;

import static org.csgames.spaceship.sdk.EventType.AIR_CONDITIONING_STARTED;
import static org.csgames.spaceship.sdk.EventType.AIR_CONDITIONING_STOPPED;
import static org.csgames.spaceship.sdk.EventType.AIR_VENT_CLOSED;
import static org.csgames.spaceship.sdk.EventType.AIR_VENT_OPEN;

public class TemperatureRegulationService {

  private final Headquarters headquarters;

  public TemperatureRegulationService(Headquarters headquarters) {
    this.headquarters = headquarters;
  }

  public void openAirVent(String target) {
    recordEvent(AIR_VENT_OPEN, target);
  }

  public void closeAirVent(String target) {
    recordEvent(AIR_VENT_CLOSED, target);
  }

  public void startAirConditioning(String target) {
    recordEvent(AIR_CONDITIONING_STARTED, target);
  }

  public void stopAirConditioning(String target) {
    recordEvent(AIR_CONDITIONING_STOPPED, target);
  }

  public void startHeating() {

  }

  public void stopHeating() {

  }

  private void recordEvent(EventType airConditioningStarted, String target) {
    Event event = new Event(airConditioningStarted, target);
    headquarters.recordEvent(event);
  }
}
