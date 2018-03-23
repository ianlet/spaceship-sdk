package org.csgames.spaceship.sdk.accept;

import org.csgames.spaceship.sdk.Headquarters;
import org.csgames.spaceship.sdk.accept.userstory.Scenario;
import org.csgames.spaceship.sdk.accept.userstory.UserStory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserStoryRunnerTest {

  private final Scenario firstScenario = new Scenario("first scenario", emptyList(), emptyList());
  private final Scenario secondScenario = new Scenario("second scenario", emptyList(), emptyList());
  private final Scenario lastScenario = new Scenario("last scenario", emptyList(), emptyList());
  private final Scenario unsuccessfulScenario = new Scenario("scenario fail", emptyList(), emptyList());

  private ScenarioRunner scenarioRunner;

  private UserStoryRunner userStoryRunner;
  private Headquarters headquarters;

  @Before
  public void setUp() throws Exception {
    scenarioRunner = mock(ScenarioRunner.class);
    willThrow(ScenarioFailedException.class).given(scenarioRunner).accept(unsuccessfulScenario);
    headquarters = mock(Headquarters.class);
    UserStoryReporter reporter = mock(UserStoryReporter.class);

    userStoryRunner = new UserStoryRunner(scenarioRunner, reporter);
  }

  @Test
  public void itShouldAcceptEachScenario() {
    List<Scenario> scenarios = asList(firstScenario, secondScenario);
    UserStory userStory = new UserStory("user story", scenarios, 3, 3, 0);

    userStoryRunner.accept(userStory);

    scenarios.forEach(scenario -> verify(scenarioRunner).accept(scenario));
  }

  @Test(expected = UserStoryFailedException.class)
  public void itShouldFailTheUserStory_givenOneOfTheScenarioFailed() {
    UserStory userStory = new UserStory("failure", asList(unsuccessfulScenario), 3, 3, 0);

    userStoryRunner.accept(userStory);
  }

  @Test
  public void itShouldAcceptEachScenarioEvenIfOneOfThemFailed() {
    List<Scenario> scenarios = asList(firstScenario, unsuccessfulScenario, lastScenario);
    UserStory userStory = new UserStory("failure", scenarios, 3, 3, 0);

    try {
      userStoryRunner.accept(userStory);
    } catch (Exception ignored) {
    }

    scenarios.forEach(scenario -> verify(scenarioRunner).accept(scenario));
  }
}
