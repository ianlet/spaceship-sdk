package org.csgames.spaceship.sdk.accept;

import org.csgames.spaceship.sdk.accept.userstory.InputEvent;
import org.csgames.spaceship.sdk.accept.userstory.Result;
import org.csgames.spaceship.sdk.accept.userstory.Scenario;

public interface ScenarioReporter {

  void reportScenarioStarted(Scenario scenario);

  void reportScenarioFailed(Scenario scenario);

  void reportScenarioSucceeded(Scenario scenario);

  void reportEventSent(InputEvent event);

  void reportEventFailed(InputEvent event);

  void reportResultUnsatisfied(Result result);

  void reportResultSatisfied(Result result);
}
