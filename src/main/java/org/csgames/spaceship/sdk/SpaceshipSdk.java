package org.csgames.spaceship.sdk;

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
    HeadquartersClientFactory clientFactory = new HeadquartersClientFactory();
    Headquarters headquarters = clientFactory.create(token);
    SpaceshipService spaceshipService = new SpaceshipService(headquarters, SpaceshipBlueprintFactory.generate());
    LocationService locationService = new LocationService();
    CommunicationService communicationService = new CommunicationService(headquarters);
    TemperatureRegulationService temperatureRegulationService = new TemperatureRegulationService(headquarters);
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
