package org.csgames.spaceship.sdk.accept;

import org.csgames.spaceship.sdk.accept.userstory.Scenario;
import org.csgames.spaceship.sdk.accept.userstory.UserStory;

import java.util.List;

public class UserStoryRunner {

  private final ScenarioRunner scenarioRunner;
  private final UserStoryReporter reporter;

  public UserStoryRunner(ScenarioRunner scenarioRunner, UserStoryReporter reporter) {
    this.scenarioRunner = scenarioRunner;
    this.reporter = reporter;
  }

  public void accept(UserStory userStory) {
    reporter.reportUserStoryStarted(userStory);
    boolean failed = acceptScenarios(userStory.scenarios);
    if (failed) {
      reporter.reportUserStoryFailed(userStory);
      throw new UserStoryFailedException();
    }
    reporter.reportUserStorySucceeded(userStory);
  }

  private boolean acceptScenarios(List<Scenario> scenarios) {
    boolean scenarioFailed = false;
    for (Scenario scenario : scenarios) {
      try {
        scenarioRunner.accept(scenario);
      } catch (ScenarioFailedException ignored) {
        scenarioFailed = true;
      }
    }
    return scenarioFailed;
  }
}
