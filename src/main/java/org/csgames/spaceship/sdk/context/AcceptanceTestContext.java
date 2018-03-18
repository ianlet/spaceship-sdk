package org.csgames.spaceship.sdk.context;

import com.mongodb.MongoClient;

import org.csgames.spaceship.sdk.CommunicationService;
import org.csgames.spaceship.sdk.EventFactory;
import org.csgames.spaceship.sdk.EventMongo;
import org.csgames.spaceship.sdk.Headquarters;
import org.csgames.spaceship.sdk.HeadquartersMongo;
import org.csgames.spaceship.sdk.LocationService;
import org.csgames.spaceship.sdk.SpaceshipBlueprint;
import org.csgames.spaceship.sdk.SpaceshipService;
import org.csgames.spaceship.sdk.TemperatureRegulationService;
import org.csgames.spaceship.sdk.accept.result.UserStoryResultFactory;
import org.csgames.spaceship.sdk.accept.result.UserStoryResultStore;
import org.csgames.spaceship.sdk.accept.result.UserStoryResultStoreMongo;
import org.csgames.spaceship.sdk.accept.userstory.UserStoryRepository;
import org.csgames.spaceship.sdk.accept.userstory.UserStoryRepositoryJsonFile;
import org.csgames.spaceship.sdk.service.AwayTeamLogService;
import org.csgames.spaceship.sdk.service.UniversalTranslatorService;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.time.Clock;

import static org.csgames.spaceship.sdk.context.ServiceLocator.register;

public class AcceptanceTestContext implements Context {

  private final String token;

  public AcceptanceTestContext(String token) {
    this.token = token;
  }

  @Override
  public void apply() {
    MongoClient mongoClient = createMongoClient();
    register(MongoClient.class, mongoClient);

    Datastore datastore = createDatastore(mongoClient);
    register(Datastore.class, datastore);

    Headquarters headquarters = new HeadquartersMongo(datastore, token);
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

    AwayTeamLogService awayTeamLogService = new AwayTeamLogService(eventFactory, headquarters);
    register(AwayTeamLogService.class, awayTeamLogService);

    UniversalTranslatorService universalTranslatorService = new UniversalTranslatorService();
    register(UniversalTranslatorService.class, universalTranslatorService);

    UserStoryRepository userStoryRepository = new UserStoryRepositoryJsonFile();
    register(UserStoryRepository.class, userStoryRepository);

    UserStoryResultStore userStoryResultStore = new UserStoryResultStoreMongo(datastore);
    register(UserStoryResultStore.class, userStoryResultStore);

    UserStoryResultFactory userStoryResultFactory = new UserStoryResultFactory(Clock.systemDefaultZone());
    register(UserStoryResultFactory.class, userStoryResultFactory);
  }

  private MongoClient createMongoClient() {
    String mongoHost = System.getProperty("mongoHost");
    int mongoPort = Integer.parseInt(System.getProperty("mongoPort"));
    return new MongoClient(mongoHost, mongoPort);
  }

  private Datastore createDatastore(MongoClient mongoClient) {
    Morphia morphia = new Morphia();
    morphia.map(EventMongo.class);
    String mongoDatabase = System.getProperty("mongoDatabase");
    Datastore datastore = morphia.createDatastore(mongoClient, mongoDatabase);
    datastore.ensureIndexes();
    return datastore;
  }
}
