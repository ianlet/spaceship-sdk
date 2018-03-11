package org.csgames.spaceship.sdk.accept.result;

public class UserStoryResultStoreFake implements UserStoryResultStore {

  @Override
  public void store(UserStoryResult userStoryResult) {
    System.out.println(String.format(
      "\uD83D\uDC27 >> Boom! User story %s has %s for team %s!",
      userStoryResult.name,
      userStoryResult.type,
      userStoryResult.team));
  }
}
