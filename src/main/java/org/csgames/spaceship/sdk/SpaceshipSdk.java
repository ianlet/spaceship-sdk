package org.csgames.spaceship.sdk;

import com.google.gson.Gson;

import org.csgames.spaceship.sdk.accept.AcceptanceService;
import org.csgames.spaceship.sdk.accept.ScenarioRunner;
import org.csgames.spaceship.sdk.accept.SpaceshipApiUnirest;
import org.csgames.spaceship.sdk.accept.UserStoryRunner;
import org.csgames.spaceship.sdk.accept.userstory.UserStoryRepository;
import org.csgames.spaceship.sdk.accept.userstory.UserStoryRepositoryJsonFile;

public class SpaceshipSdk {

  private final String token;
  private final Headquarters headquarters;
  private final EventFactory eventFactory;
  private final SpaceshipService spaceshipService;
  private final LocationService locationService;
  private final CommunicationService communicationService;
  private final TemperatureRegulationService temperatureRegulationService;

  private SpaceshipSdk(String token, Headquarters headquarters, EventFactory eventFactory, SpaceshipService spaceshipService, LocationService locationService, CommunicationService communicationService, TemperatureRegulationService temperatureRegulationService) {
    this.token = token;
    this.headquarters = headquarters;
    this.eventFactory = eventFactory;
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
    EventFactory eventFactory = new EventFactory();
    SpaceshipService spaceshipService = new SpaceshipService(headquarters, SpaceshipBlueprintFactory.generate(), eventFactory);
    CommunicationService communicationService = new CommunicationService(headquarters, eventFactory);
    TemperatureRegulationService temperatureRegulationService = new TemperatureRegulationService(headquarters, eventFactory);

    return new SpaceshipSdk(token, headquarters, eventFactory, spaceshipService, locationService, communicationService, temperatureRegulationService);
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

  public AcceptanceService createAcceptanceService(int port) {
    Gson gson = new Gson();
    SpaceshipApiUnirest api = new SpaceshipApiUnirest(port, gson);
    UserStoryRepository userStoryRepository = new UserStoryRepositoryJsonFile();
    ScenarioRunner scenarioRunner = new ScenarioRunner(api, headquarters);
    UserStoryRunner userStoryRunner = new UserStoryRunner(scenarioRunner);
    return new AcceptanceService(token, userStoryRepository, userStoryRunner, headquarters, eventFactory);
  }
}
