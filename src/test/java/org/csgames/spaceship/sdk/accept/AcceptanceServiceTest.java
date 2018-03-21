package org.csgames.spaceship.sdk.accept;

import org.csgames.spaceship.sdk.accept.result.UserStoryResult;
import org.csgames.spaceship.sdk.accept.result.UserStoryResultFactory;
import org.csgames.spaceship.sdk.accept.result.UserStoryResultStore;
import org.csgames.spaceship.sdk.accept.result.UserStoryResultType;
import org.csgames.spaceship.sdk.accept.userstory.UserStory;
import org.csgames.spaceship.sdk.accept.userstory.UserStoryRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AcceptanceServiceTest {

  private static final String TEAM_TOKEN = "team-01";

  private final UserStory firstUserStory = new UserStory("do something", emptyList(), 3,3,0);
  private final UserStory secondUserStory = new UserStory("do something else", emptyList(),3,3,0);

  private UserStoryRepository userStoryRepository;
  private UserStoryRunner userStoryRunner;
  private UserStoryResultFactory userStoryResultFactory;
  private UserStoryResultStore userStoryResultStore;

  private AcceptanceService acceptanceService;

  @Before
  public void setUp() throws Exception {
    Clock clock = setUpClock();
    userStoryResultFactory = new UserStoryResultFactory(clock);
    userStoryRepository = mock(UserStoryRepository.class);
    userStoryRunner = mock(UserStoryRunner.class);
    userStoryResultStore = mock(UserStoryResultStore.class);

    acceptanceService = new AcceptanceService(TEAM_TOKEN, userStoryRepository, userStoryRunner, userStoryResultFactory, userStoryResultStore);
  }

  private Clock setUpClock() {
    Clock clock = mock(Clock.class);
    willReturn(Instant.now()).given(clock).instant();
    return clock;
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

    UserStoryResult userStorySucceeded = userStoryResultFactory.create(UserStoryResultType.SUCCEEDED, TEAM_TOKEN, userStory.name);
    verify(userStoryResultStore).store(userStorySucceeded);
  }

  @Test
  public void itShouldRecordThatTheUserStoryFailed_givenUnsuccessfulUserStory() {
    UserStory userStory = givenUnsuccessfulUserStory();

    acceptanceService.acceptUserStories();

    UserStoryResult userStoryFailed = userStoryResultFactory.create(UserStoryResultType.FAILED, TEAM_TOKEN, userStory.name);
    verify(userStoryResultStore).store(userStoryFailed);
  }

  private UserStory givenSuccessfulUserStory() {
    UserStory userStory = new UserStory("success", emptyList(),3,3,0);
    willReturn(asList(userStory)).given(userStoryRepository).findAll();
    return userStory;
  }

  private UserStory givenUnsuccessfulUserStory() {
    UserStory userStory = new UserStory("failure", emptyList(),3,3,0);
    willReturn(asList(userStory)).given(userStoryRepository).findAll();
    willThrow(UserStoryFailedException.class).given(userStoryRunner).accept(userStory);
    return userStory;
  }
}
