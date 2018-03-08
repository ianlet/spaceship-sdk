package org.csgames.spaceship.sdk.accept;

import org.csgames.spaceship.sdk.Event;
import org.csgames.spaceship.sdk.EventFactory;
import org.csgames.spaceship.sdk.EventType;
import org.csgames.spaceship.sdk.Headquarters;
import org.csgames.spaceship.sdk.accept.userstory.UserStory;
import org.csgames.spaceship.sdk.accept.userstory.UserStoryRepository;
import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.csgames.spaceship.sdk.EventType.USER_STORY_SUCCEEDED;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AcceptanceServiceTest {

  private static final String TEAM_TOKEN = "team-01";

  private final UserStory firstUserStory = new UserStory("do something", emptyList());
  private final UserStory secondUserStory = new UserStory("do something else", emptyList());

  private UserStoryRepository userStoryRepository;
  private UserStoryRunner userStoryRunner;
  private Headquarters headquarters;
  private EventFactory eventFactory;

  private AcceptanceService acceptanceService;

  @Before
  public void setUp() throws Exception {
    eventFactory = new EventFactory();
    userStoryRepository = mock(UserStoryRepository.class);
    userStoryRunner = mock(UserStoryRunner.class);
    headquarters = mock(Headquarters.class);

    acceptanceService = new AcceptanceService(TEAM_TOKEN, userStoryRepository, userStoryRunner, headquarters, eventFactory);
  }

  @Test
  public void itShouldAcceptEachUserStory() {
    willReturn(asList(firstUserStory, secondUserStory)).given(userStoryRepository).findAll();

    acceptanceService.acceptUserStories();

    verify(userStoryRunner).accept(firstUserStory);
    verify(userStoryRunner).accept(secondUserStory);
  }

  @Test
  public void itShouldRecordThatTheUserStorySucceeded_givenSuccessfulUserStory() {
    UserStory userStory = givenSuccessfulUserStory();

    acceptanceService.acceptUserStories();

    Event userStorySucceeded = eventFactory.create(USER_STORY_SUCCEEDED, TEAM_TOKEN, userStory.name);
    verify(headquarters).recordEvent(userStorySucceeded);
  }

  @Test
  public void itShouldRecordThatTheUserStoryFailed_givenUnsuccessfulUserStory() {
    UserStory userStory = givenUnsuccessfulUserStory();

    acceptanceService.acceptUserStories();

    Event userStoryFailed = eventFactory.create(EventType.USER_STORY_FAILED, TEAM_TOKEN, userStory.name);
    verify(headquarters).recordEvent(userStoryFailed);
  }

  private UserStory givenSuccessfulUserStory() {
    UserStory userStory = new UserStory("success", emptyList());
    willReturn(asList(userStory)).given(userStoryRepository).findAll();
    return userStory;
  }

  private UserStory givenUnsuccessfulUserStory() {
    UserStory userStory = new UserStory("failure", emptyList());
    willReturn(asList(userStory)).given(userStoryRepository).findAll();
    willThrow(UserStoryFailedException.class).given(userStoryRunner).accept(userStory);
    return userStory;
  }
}
