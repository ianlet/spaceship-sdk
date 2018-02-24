package org.csgames.spaceship.sdk;

public class SpaceshipSdk {

  private final SpaceshipService spaceshipService;
  private final LocationService locationService;
  private final CommunicationService communicationService;
  private final TemperatureLocationService temperatureLocationService;

  private SpaceshipSdk(SpaceshipService spaceshipService, LocationService locationService, CommunicationService communicationService, TemperatureLocationService temperatureLocationService) {
    this.spaceshipService = spaceshipService;
    this.locationService = locationService;
    this.communicationService = communicationService;
    this.temperatureLocationService = temperatureLocationService;
  }

  public static SpaceshipSdk register(String token) {
    HeadquartersClientFactory clientFactory = new HeadquartersClientFactory();
    Headquarters headquarters = clientFactory.create(token);
    SpaceshipService spaceshipService = new SpaceshipService(headquarters, SpaceshipBlueprintFactory.generate());
    LocationService locationService = new LocationService();
    CommunicationService communicationService = new CommunicationService(headquarters);
    TemperatureLocationService temperatureLocationService = new TemperatureLocationService(headquarters);
    return new SpaceshipSdk(spaceshipService, locationService, communicationService, temperatureLocationService);
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

  public TemperatureLocationService getTemperatureLocationService() {
    return temperatureLocationService;
  }
}
