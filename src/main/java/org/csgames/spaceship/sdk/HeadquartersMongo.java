package org.csgames.spaceship.sdk;

import org.mongodb.morphia.Datastore;

public class HeadquartersMongo implements Headquarters {

  private final Datastore datastore;
  private final String token;

  public HeadquartersMongo(Datastore datastore, String token) {
    this.datastore = datastore;
    this.token = token;
  }

  @Override
  public void recordEvent(Event event) {
    EventMongo recordedEvent = new EventMongo(token, event.type, event.target, event.payload, event.timestamp);
    datastore.save(recordedEvent);
  }
}
