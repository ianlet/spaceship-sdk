package org.csgames.spaceship.sdk.accept.userstory;

import java.util.List;

public interface UserStoryRepository {

  List<UserStory> findAll();
}
