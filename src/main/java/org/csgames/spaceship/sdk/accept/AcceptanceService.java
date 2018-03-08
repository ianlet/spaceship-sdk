package org.csgames.spaceship.sdk.accept;

import org.csgames.spaceship.sdk.Event;
import org.csgames.spaceship.sdk.EventFactory;
import org.csgames.spaceship.sdk.EventType;
import org.csgames.spaceship.sdk.Headquarters;
import org.csgames.spaceship.sdk.accept.userstory.UserStory;
import org.csgames.spaceship.sdk.accept.userstory.UserStoryRepository;

import java.util.List;

import static org.csgames.spaceship.sdk.EventType.USER_STORY_FAILED;
import static org.csgames.spaceship.sdk.EventType.USER_STORY_SUCCEEDED;

public class AcceptanceService {

  private final String teamToken;
  private final UserStoryRepository userStoryRepository;
  private final UserStoryRunner userStoryRunner;
  private final Headquarters headquarters;
  private final EventFactory eventFactory;

  public AcceptanceService(String teamToken, UserStoryRepository userStoryRepository, UserStoryRunner userStoryRunner, Headquarters headquarters, EventFactory eventFactory) {
    this.teamToken = teamToken;
    this.userStoryRepository = userStoryRepository;
    this.userStoryRunner = userStoryRunner;
    this.headquarters = headquarters;
    this.eventFactory = eventFactory;
  }

  public void acceptUserStories() {
    List<UserStory> userStories = userStoryRepository.findAll();
    userStories.forEach(userStory -> acceptUserStory(teamToken, userStory));
  }

  private void acceptUserStory(String teamToken, UserStory userStory) {
    try {
      userStoryRunner.accept(userStory);
      recordEvent(USER_STORY_SUCCEEDED, userStory, teamToken);
    } catch (UserStoryFailedException e) {
      recordEvent(USER_STORY_FAILED, userStory, teamToken);
    }
  }

  private void recordEvent(EventType eventType, UserStory userStory, String teamToken) {
    Event event = eventFactory.create(eventType, teamToken, userStory.name);
    headquarters.recordEvent(event);
  }
}
