package org.csgames.spaceship.sdk;

public class TemperatureRegulationService {

  private final Headquarters headquarters;

  public TemperatureRegulationService(Headquarters headquarters) {
    this.headquarters = headquarters;
  }

  public void openAirVent(String target) {
    headquarters.recordEvent(new Event(EventType.AIR_VENT_OPEN, target));
  }

  public void closeAirVent(String target) {
    headquarters.recordEvent(new Event(EventType.AIR_VENT_CLOSED, target));
  }

  public void startAirConditioning() {

  }

  public void stopAirConditioning() {

  }

  public void startHeating() {

  }

  public void stopHeating() {

  }
}
