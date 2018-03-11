package org.csgames.spaceship.sdk.context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.csgames.spaceship.sdk.CommunicationService;
import org.csgames.spaceship.sdk.EventFactory;
import org.csgames.spaceship.sdk.Headquarters;
import org.csgames.spaceship.sdk.HeadquartersFake;
import org.csgames.spaceship.sdk.LocationService;
import org.csgames.spaceship.sdk.SpaceshipBlueprint;
import org.csgames.spaceship.sdk.SpaceshipService;
import org.csgames.spaceship.sdk.TemperatureRegulationService;
import org.csgames.spaceship.sdk.accept.userstory.UserStory;
import org.csgames.spaceship.sdk.accept.userstory.UserStoryDeserializer;
import org.csgames.spaceship.sdk.accept.userstory.UserStoryRepository;
import org.csgames.spaceship.sdk.accept.userstory.UserStoryRepositoryFake;

import static org.csgames.spaceship.sdk.context.ServiceLocator.register;

public class FakeContext implements Context {

  private final String token;

  public FakeContext(String token) {
    this.token = token;
  }

  @Override
  public void apply() {
    Headquarters headquarters = new HeadquartersFake(token);
    register(Headquarters.class, headquarters);

    EventFactory eventFactory = new EventFactory();
    register(EventFactory.class, eventFactory);

    CommunicationService communicationService = new CommunicationService(headquarters, eventFactory);
    register(CommunicationService.class, communicationService);

    LocationService locationService = new LocationService();
    register(LocationService.class, locationService);

    TemperatureRegulationService temperatureRegulationService = new TemperatureRegulationService(headquarters, eventFactory);
    register(TemperatureRegulationService.class, temperatureRegulationService);

    SpaceshipBlueprint spaceshipBlueprint = new SpaceshipBlueprint();
    register(SpaceshipBlueprint.class, spaceshipBlueprint);

    SpaceshipService spaceshipService = new SpaceshipService(headquarters, spaceshipBlueprint, eventFactory);
    register(SpaceshipService.class, spaceshipService);

    UserStoryRepository userStoryRepository = new UserStoryRepositoryFake();
    register(UserStoryRepository.class, userStoryRepository);

    UserStoryDeserializer userStoryDeserializer = new UserStoryDeserializer();
    register(UserStoryDeserializer.class, userStoryDeserializer);

    Gson gson = new GsonBuilder().registerTypeAdapter(UserStory.class, userStoryDeserializer).create();
    register(Gson.class, gson);
  }
}
