package org.csgames.spaceship.sdk;

import java.time.Clock;

public class SpaceshipSdk {

  private final SpaceshipService spaceshipService;
  private final LocationService locationService;
  private final CommunicationService communicationService;
  private final TemperatureRegulationService temperatureRegulationService;

  private SpaceshipSdk(SpaceshipService spaceshipService, LocationService locationService, CommunicationService communicationService, TemperatureRegulationService temperatureRegulationService) {
    this.spaceshipService = spaceshipService;
    this.locationService = locationService;
    this.communicationService = communicationService;
    this.temperatureRegulationService = temperatureRegulationService;
  }

  public static SpaceshipSdk register(String token) {
    if (token == null || token.isEmpty()) throw new RuntimeException("Cannot register team without a token. Please, provide a token with -DteamToken=TOKEN");

    HeadquartersFactory headquartersFactory = new HeadquartersFactory();
    Headquarters headquarters = headquartersFactory.create(token);
    LocationService locationService = new LocationService();
    EventFactory eventFactory = new EventFactory(Clock.systemDefaultZone());
    SpaceshipService spaceshipService = new SpaceshipService(headquarters, SpaceshipBlueprintFactory.generate(), eventFactory);
    CommunicationService communicationService = new CommunicationService(headquarters, eventFactory);
    TemperatureRegulationService temperatureRegulationService = new TemperatureRegulationService(headquarters, eventFactory);

    return new SpaceshipSdk(spaceshipService, locationService, communicationService, temperatureRegulationService);
  }

  public SpaceshipService getSpaceshipService() {
    return spaceshipService;
  }

  public LocationService getLocationService() {
    return locationService;
  }

  public CommunicationService getCommunicationService() {
    return communicationService;
  }

  public TemperatureRegulationService getTemperatureRegulationService() {
    return temperatureRegulationService;
  }
}
