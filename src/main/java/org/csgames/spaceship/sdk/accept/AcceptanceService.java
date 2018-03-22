package org.csgames.spaceship.sdk.accept;

import org.csgames.spaceship.sdk.accept.result.UserStoryResult;
import org.csgames.spaceship.sdk.accept.result.UserStoryResultFactory;
import org.csgames.spaceship.sdk.accept.result.UserStoryResultStore;
import org.csgames.spaceship.sdk.accept.result.UserStoryResultType;
import org.csgames.spaceship.sdk.accept.userstory.UserStory;
import org.csgames.spaceship.sdk.accept.userstory.UserStoryRepository;

import java.util.List;

import static org.csgames.spaceship.sdk.accept.result.UserStoryResultType.FAILED;
import static org.csgames.spaceship.sdk.accept.result.UserStoryResultType.SUCCEEDED;

public class AcceptanceService {

  private final String teamToken;
  private final UserStoryRepository userStoryRepository;
  private final UserStoryRunner userStoryRunner;
  private final UserStoryResultFactory resultFactory;
  private final UserStoryResultStore resultStore;

  public AcceptanceService(String teamToken,
                           UserStoryRepository userStoryRepository,
                           UserStoryRunner userStoryRunner,
                           UserStoryResultFactory resultFactory,
                           UserStoryResultStore resultStore) {
    this.teamToken = teamToken;
    this.userStoryRepository = userStoryRepository;
    this.userStoryRunner = userStoryRunner;
    this.resultFactory = resultFactory;
    this.resultStore = resultStore;
  }

  public void acceptUserStories() {
    List<UserStory> userStories = userStoryRepository.findAll();
    userStories.forEach(userStory -> acceptUserStory(teamToken, userStory));
  }

  private void acceptUserStory(String teamToken, UserStory userStory) {
    try {
      userStoryRunner.accept(userStory);
      recordResult(SUCCEEDED, userStory, teamToken);
    } catch (UserStoryFailedException e) {
      recordResult(FAILED, userStory, teamToken);
    }
  }

  private void recordResult(UserStoryResultType eventType, UserStory userStory, String teamToken) {
    UserStoryResult result = resultFactory.create(eventType, teamToken, userStory.name, userStory.points, userStory.penalties, userStory.deaths);
    resultStore.store(result);
  }
}
