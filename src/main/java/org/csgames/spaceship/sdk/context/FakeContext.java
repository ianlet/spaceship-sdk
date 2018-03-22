package org.csgames.spaceship.sdk.context;

import org.csgames.spaceship.sdk.*;
import org.csgames.spaceship.sdk.accept.result.UserStoryResultFactory;
import org.csgames.spaceship.sdk.accept.result.UserStoryResultStore;
import org.csgames.spaceship.sdk.accept.result.UserStoryResultStoreFake;
import org.csgames.spaceship.sdk.accept.userstory.UserStoryRepository;
import org.csgames.spaceship.sdk.accept.userstory.UserStoryRepositoryFake;
import org.csgames.spaceship.sdk.service.AwayTeamLogService;
import org.csgames.spaceship.sdk.service.PlanetResourceService;

import java.time.Clock;
import java.util.ArrayList;

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

    SpaceshipBlueprint spaceshipBlueprint = new SpaceshipBlueprint(new ArrayList<Room>());
    register(SpaceshipBlueprint.class, spaceshipBlueprint);

    TemperatureReader temperatureReader = new TemperatureReader();
    register(TemperatureReader.class, temperatureReader);

    SpaceshipService spaceshipService = new SpaceshipService(headquarters, spaceshipBlueprint, eventFactory, temperatureReader);
    register(SpaceshipService.class, spaceshipService);

    AwayTeamLogService awayTeamLogService = new AwayTeamLogService(eventFactory, headquarters);
    register(AwayTeamLogService.class, awayTeamLogService);

    PlanetResourceService planetResourceService = new PlanetResourceService(eventFactory, headquarters);
    register(PlanetResourceService.class, planetResourceService);

    UserStoryRepository userStoryRepository = new UserStoryRepositoryFake();
    register(UserStoryRepository.class, userStoryRepository);

    UserStoryResultStore userStoryResultStore = new UserStoryResultStoreFake();
    register(UserStoryResultStore.class, userStoryResultStore);

    UserStoryResultFactory userStoryResultFactory = new UserStoryResultFactory(Clock.systemDefaultZone());
    register(UserStoryResultFactory.class, userStoryResultFactory);
  }
}
