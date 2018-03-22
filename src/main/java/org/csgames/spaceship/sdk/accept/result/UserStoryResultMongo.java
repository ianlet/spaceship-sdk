package org.csgames.spaceship.sdk.accept.result;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.time.Instant;

@Entity("results")
public class UserStoryResultMongo {

  @Id
  public String id;
  public String userStory;
  public String team;
  public UserStoryResultType result;
  public final int points;
  public final int penalties;
  public final double deaths;
  public Instant timestamp;

  public UserStoryResultMongo(String userStory, String team, UserStoryResultType result, int points, int penalties, double deaths, Instant timestamp) {
    this.userStory = userStory;
    this.team = team;
    this.result = result;
    this.points = points;
    this.penalties = penalties;
    this.deaths = deaths;
    this.timestamp = timestamp;
  }
}
