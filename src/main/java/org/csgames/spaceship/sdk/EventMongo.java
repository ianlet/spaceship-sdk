package org.csgames.spaceship.sdk;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.time.Instant;

@Entity("events")
public class EventMongo {

  @Id
  public String id;
  public String team;
  public EventType type;
  public String target;
  public String payload;
  public Instant timestamp;

  public EventMongo(String team, EventType type, String target, String payload, Instant timestamp) {
    this.team = team;
    this.type = type;
    this.target = target;
    this.payload = payload;
    this.timestamp = timestamp;
  }

  public EventMongo() {
  }
}
