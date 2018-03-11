package org.csgames.spaceship.sdk.accept.userstory;

import java.util.Collections;
import java.util.List;

public class UserStoryRepositoryFake implements UserStoryRepository {

  @Override
  public List<UserStory> findAll() {
    return Collections.emptyList();
  }
}
