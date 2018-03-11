package org.csgames.spaceship.sdk.accept.result;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("results")
public class UserStoryResultMongo {

  @Id
  public String id;
  public String userStory;
  public String team;
  public UserStoryResultType result;

  public UserStoryResultMongo(String userStory, String team, UserStoryResultType result) {
    this.userStory = userStory;
    this.team = team;
    this.result = result;
  }
}
