package org.csgames.spaceship.sdk;

public class SpaceshipSdk {

  private final SpaceshipService spaceshipService;
  private final LocationService locationService;
  private final CommunicationService communicationService;

  private SpaceshipSdk(SpaceshipService spaceshipService, LocationService locationService, CommunicationService communicationService) {
    this.spaceshipService = spaceshipService;
    this.locationService = locationService;
    this.communicationService = communicationService;
  }

  public static SpaceshipSdk register(String token) {
    HeadquartersClientFactory clientFactory = new HeadquartersClientFactory();
    HeadquartersClient headquartersClient = clientFactory.create(token);
    SpaceshipService spaceshipService = new SpaceshipService(headquartersClient, SpaceshipBlueprintFactory.generate());
    LocationService locationService = new LocationService();
    CommunicationService communicationService = new CommunicationService(headquartersClient);
    return new SpaceshipSdk(spaceshipService, locationService, communicationService);
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
}
