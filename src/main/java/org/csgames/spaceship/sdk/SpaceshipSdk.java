package org.csgames.spaceship.sdk;

import com.google.gson.Gson;

import org.csgames.spaceship.sdk.accept.AcceptanceService;
import org.csgames.spaceship.sdk.accept.ScenarioRunner;
import org.csgames.spaceship.sdk.accept.SpaceshipApi;
import org.csgames.spaceship.sdk.accept.SpaceshipApiUnirest;
import org.csgames.spaceship.sdk.accept.UserStoryRunner;
import org.csgames.spaceship.sdk.accept.userstory.UserStoryRepository;
import org.csgames.spaceship.sdk.context.Context;
import org.csgames.spaceship.sdk.context.ContextFactory;

import static org.csgames.spaceship.sdk.context.ServiceLocator.locate;


public class SpaceshipSdk {

  private final String token;

  private SpaceshipSdk(String token) {
    this.token = token;
  }

  public static SpaceshipSdk register(String token) {
    if (token == null || token.isEmpty()) throw new RuntimeException("Cannot register team without a token. Please, provide a token with -DteamToken=TOKEN");

    Context context = new ContextFactory().create(token);
    context.apply();

    return new SpaceshipSdk(token);
  }

  public SpaceshipService getSpaceshipService() {
    return locate(SpaceshipService.class);
  }

  public LocationService getLocationService() {
    return locate(LocationService.class);
  }

  public CommunicationService getCommunicationService() {
    return locate(CommunicationService.class);
  }

  public TemperatureRegulationService getTemperatureRegulationService() {
    return locate(TemperatureRegulationService.class);
  }

  public AcceptanceService createAcceptanceService(int port) {
    UserStoryRepository userStoryRepository = locate(UserStoryRepository.class);
    Headquarters headquarters = locate(Headquarters.class);
    Gson gson = locate(Gson.class);
    SpaceshipApi spaceshipApi = new SpaceshipApiUnirest(port, gson);
    ScenarioRunner scenarioRunner = new ScenarioRunner(spaceshipApi, headquarters);
    UserStoryRunner userStoryRunner = new UserStoryRunner(scenarioRunner);
    EventFactory eventFactory = locate(EventFactory.class);
    return new AcceptanceService(token, userStoryRepository, userStoryRunner, headquarters, eventFactory);
  }
}
