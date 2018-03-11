package org.csgames.spaceship.sdk;

import com.google.common.collect.Sets;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;

import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import static com.google.common.truth.Truth.assertThat;

public class HeadquartersMongoTest {

  private static final String DATABASE_NAME = "spaceship-test";
  private static final String TEAM_TOKEN = "team-space-seals";

  private static final String RECORDED_EVENT_TARGET = "team-01";
  private static final String RECORDED_EVENT_PAYLOAD = "34.654334,27.384293";
  private static final EventType RECORDED_EVENT_TYPE = EventType.MOVED_TO;

  private static final EventType UNRECORDED_EVENT_TYPE = EventType.FISH_CAUGHT;
  private static final String UNRECORDED_EVENT_TARGET = "no-target";
  private static final String UNRECORDED_EVENT_PAYLOAD = "no-payload";

  private Datastore datastore;

  private HeadquartersMongo headquarters;
  private EventFactory eventFactory;

  @Before
  public void setUp() throws Exception {
    eventFactory = new EventFactory();
    datastore = setUpMongo();

    headquarters = new HeadquartersMongo(datastore, TEAM_TOKEN);
  }

  @Test
  public void itShouldPersistTheRecordedEventForTheTeam() {
    Event recordedEvent = eventFactory.create(RECORDED_EVENT_TYPE, RECORDED_EVENT_TARGET, RECORDED_EVENT_PAYLOAD);

    headquarters.recordEvent(recordedEvent);

    assertThat(headquarters.wasEventRecorded(recordedEvent)).isTrue();
  }

  @Test
  public void itShouldReturnTrue_givenTheEventWasRecorded() {
    Event recordedEvent = eventFactory.create(RECORDED_EVENT_TYPE, RECORDED_EVENT_TARGET, RECORDED_EVENT_PAYLOAD);

    headquarters.recordEvent(recordedEvent);

    assertThat(headquarters.wasEventRecorded(recordedEvent)).isTrue();
  }

  @Test
  public void itShouldReturnFalse_givenTheEventWasNotRecorded() {
    Event recordedEvent = eventFactory.create(UNRECORDED_EVENT_TYPE, UNRECORDED_EVENT_TARGET, UNRECORDED_EVENT_PAYLOAD);

    assertThat(headquarters.wasEventRecorded(recordedEvent)).isFalse();
  }

  @Test
  public void itShouldReturnFalse_givenNoEventRecorded() {
    assertThat(headquarters.hasRecordedAnyEvent()).isFalse();
  }

  @Test
  public void itShouldReturnTrue_givenAnEventWasRecorded() {
    Event recordedEvent = eventFactory.create(RECORDED_EVENT_TYPE, RECORDED_EVENT_TARGET, RECORDED_EVENT_PAYLOAD);

    headquarters.recordEvent(recordedEvent);

    assertThat(headquarters.hasRecordedAnyEvent()).isTrue();
  }

  @Test
  public void itShouldPurgeRecordedEvents() {
    Event recordedEvent = eventFactory.create(RECORDED_EVENT_TYPE, RECORDED_EVENT_TARGET, RECORDED_EVENT_PAYLOAD);
    headquarters.recordEvent(recordedEvent);

    headquarters.purgeEvents();

    assertThat(headquarters.hasRecordedAnyEvent()).isFalse();
  }

  private Datastore setUpMongo() {
    Morphia morphia = new Morphia(Sets.newHashSet(EventMongo.class));
    MongoClient mongoClient = new Fongo(DATABASE_NAME).getMongo();
    Datastore datastore = morphia.createDatastore(mongoClient, DATABASE_NAME);
    datastore.ensureIndexes();
    return datastore;
  }
}
