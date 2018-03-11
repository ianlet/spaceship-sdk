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
    EventMongo recordedEvent = assembleRecordedEvent(event);
    datastore.save(recordedEvent);
  }

  private EventMongo assembleRecordedEvent(Event event) {
    return new EventMongo(token, event.type, event.target, event.payload);
  }

  @Override
  public boolean wasEventRecorded(Event event) {
    EventMongo recordedEvent = datastore.find(EventMongo.class)
      .field("team").equal(token)
      .field("target").equal(event.target)
      .field("type").equal(event.type)
      .field("payload").equal(event.payload).get();
    return recordedEvent != null;
  }

  @Override
  public boolean hasRecordedAnyEvent() {
    return datastore.find(EventMongo.class).get() != null;
  }

  @Override
  public void purgeEvents() {
    datastore.delete(datastore.createQuery(EventMongo.class));
  }
}
