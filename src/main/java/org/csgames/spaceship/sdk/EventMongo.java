package org.csgames.spaceship.sdk;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("events")
public class EventMongo {

  @Id
  public String id;
  public String team;
  public EventType type;
  public String target;
  public String payload;

  public EventMongo(String team, EventType type, String target, String payload) {
    this.team = team;
    this.type = type;
    this.target = target;
    this.payload = payload;
  }

  public EventMongo() {
  }
}
