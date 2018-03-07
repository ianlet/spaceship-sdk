package org.csgames.spaceship.sdk;

import static org.csgames.spaceship.sdk.EventType.AIR_CONDITIONING_STARTED;
import static org.csgames.spaceship.sdk.EventType.AIR_CONDITIONING_STOPPED;
import static org.csgames.spaceship.sdk.EventType.AIR_VENT_CLOSED;
import static org.csgames.spaceship.sdk.EventType.AIR_VENT_OPEN;
import static org.csgames.spaceship.sdk.EventType.HEATING_STARTED;
import static org.csgames.spaceship.sdk.EventType.HEATING_STOPPED;

public class TemperatureRegulationService {

  private final Headquarters headquarters;
  private final EventFactory eventFactory;

  public TemperatureRegulationService(Headquarters headquarters, EventFactory eventFactory) {
    this.headquarters = headquarters;
    this.eventFactory = eventFactory;
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

  public void startHeating(String target) {
    recordEvent(HEATING_STARTED, target);
  }

  public void stopHeating(String target) {
    recordEvent(HEATING_STOPPED, target);
  }

  private void recordEvent(EventType airConditioningStarted, String target) {
    Event event = eventFactory.create(airConditioningStarted, target);
    headquarters.recordEvent(event);
  }
}
