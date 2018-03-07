package org.csgames.spaceship.sdk;

import com.mongodb.MongoClient;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

class HeadquartersFactory {

  Headquarters create(String token) {
    HeadquartersType headquartersType = HeadquartersType.fromString(System.getProperty("headquartersType"));
    switch (headquartersType) {
      case ACCEPTANCE_TESTS:
        Datastore datastore = setUpMongo();
        return new HeadquartersMongo(datastore, token);
      case FAKE:
      default:
        return new HeadquartersFake(token);
    }
  }

  private Datastore setUpMongo() {
    MongoClient mongoClient = createMongoClient();
    return createDatastore(mongoClient);
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
