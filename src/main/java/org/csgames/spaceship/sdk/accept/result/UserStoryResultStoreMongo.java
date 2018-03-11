package org.csgames.spaceship.sdk.accept.result;

import org.mongodb.morphia.Datastore;

public class UserStoryResultStoreMongo implements UserStoryResultStore {

  private final Datastore datastore;

  public UserStoryResultStoreMongo(Datastore datastore) {
    this.datastore = datastore;
  }

  @Override
  public void store(UserStoryResult userStoryResult) {
    UserStoryResultMongo result = assembleResult(userStoryResult);
    datastore.save(result);
  }

  private UserStoryResultMongo assembleResult(UserStoryResult userStoryResult) {
    return new UserStoryResultMongo(userStoryResult.name, userStoryResult.team, userStoryResult.type);
  }
}
