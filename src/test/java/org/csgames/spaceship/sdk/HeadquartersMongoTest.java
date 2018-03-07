package org.csgames.spaceship.sdk;

import com.google.common.collect.Sets;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;

import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.time.Clock;
import java.time.Instant;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

public class HeadquartersMongoTest {

  private static final String TEAM_TOKEN = "team-space-seals";
  private static final String RECORDED_EVENT_TARGET = "team-01";
  private static final String RECORDED_EVENT_PAYLOAD = "34.654334,27.384293";
  private static final EventType RECORDED_EVENT_TYPE = EventType.MOVED_TO;
  private static final String DATABASE_NAME = "spaceship-test";

  private Datastore datastore;

  private HeadquartersMongo headquarters;
  private EventFactory eventFactory;

  @Before
  public void setUp() throws Exception {
    Clock clock = setUpClock();
    eventFactory = new EventFactory(clock);
    datastore = setUpMongo();

    headquarters = new HeadquartersMongo(datastore, TEAM_TOKEN);
  }

  @Test
  public void itShouldPersistTheRecordedEventForTheTeam() {
    Event recordedEvent = eventFactory.create(RECORDED_EVENT_TYPE, RECORDED_EVENT_TARGET, RECORDED_EVENT_PAYLOAD);

    headquarters.recordEvent(recordedEvent);

    assertThatTheRecordedEventIsPersisted(recordedEvent);
  }

  private void assertThatTheRecordedEventIsPersisted(Event recordedEvent) {
    EventMongo persistedEvent = datastore.find(EventMongo.class).get();
    assertThat(persistedEvent.team).isEqualTo(TEAM_TOKEN);
    assertThat(persistedEvent.type).isEqualTo(recordedEvent.type);
    assertThat(persistedEvent.target).isEqualTo(recordedEvent.target);
    assertThat(persistedEvent.payload).isEqualTo(recordedEvent.payload);
    assertThat(persistedEvent.timestamp).isEqualTo(recordedEvent.timestamp);
  }

  private Clock setUpClock() {
    Clock clock = mock(Clock.class);
    willReturn(Instant.now()).given(clock).instant();
    return clock;
  }

  private Datastore setUpMongo() {
    Morphia morphia = new Morphia(Sets.newHashSet(EventMongo.class));
    MongoClient mongoClient = new Fongo(DATABASE_NAME).getMongo();
    Datastore datastore = morphia.createDatastore(mongoClient, DATABASE_NAME);
    datastore.ensureIndexes();
    return datastore;
  }
}
