package org.csgames.spaceship.sdk.accept;

import org.csgames.spaceship.sdk.accept.userstory.Scenario;
import org.csgames.spaceship.sdk.accept.userstory.UserStory;

import java.util.List;

public class UserStoryRunner {

  private final ScenarioRunner scenarioRunner;

  public UserStoryRunner(ScenarioRunner scenarioRunner) {
    this.scenarioRunner = scenarioRunner;
  }

  public void accept(UserStory userStory) {
    boolean failed = acceptScenarios(userStory.scenarios);
    if (failed) {
      throw new UserStoryFailedException();
    }
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
