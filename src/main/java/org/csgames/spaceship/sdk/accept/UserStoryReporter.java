package org.csgames.spaceship.sdk.accept;

import org.csgames.spaceship.sdk.accept.userstory.UserStory;

public interface UserStoryReporter {

  void reportUserStoryStarted(UserStory userStory);

  void reportUserStoryFailed(UserStory userStory);

  void reportUserStorySucceeded(UserStory userStory);
}
