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
  public Instant timestamp;

  public UserStoryResultMongo(String userStory, String team, UserStoryResultType result, Instant timestamp) {
    this.userStory = userStory;
    this.team = team;
    this.result = result;
    this.timestamp = timestamp;
  }
}
