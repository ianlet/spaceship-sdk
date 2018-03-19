package org.csgames.spaceship.sdk.accept.userstory;

import org.junit.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class UserStoryRepositoryJsonFileTest {

  @Test
  public void itShouldFindAllUserStories() {
    UserStoryRepositoryJsonFile userStoryRepositoryJsonFile = new UserStoryRepositoryJsonFile();

    List<UserStory> userStories = userStoryRepositoryJsonFile.findAll();

    assertThat(userStories).isNotEmpty();
  }
}
