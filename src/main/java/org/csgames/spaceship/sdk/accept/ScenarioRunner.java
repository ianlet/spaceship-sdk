package org.csgames.spaceship.sdk.accept;

import org.csgames.spaceship.sdk.Event;
import org.csgames.spaceship.sdk.Headquarters;
import org.csgames.spaceship.sdk.accept.userstory.InputEvent;
import org.csgames.spaceship.sdk.accept.userstory.Result;
import org.csgames.spaceship.sdk.accept.userstory.Scenario;

public class ScenarioRunner {

  private final SpaceshipApi spaceshipApi;
  private final Headquarters headquarters;
  private final ScenarioReporter reporter;

  public ScenarioRunner(SpaceshipApi spaceshipApi, Headquarters headquarters, ScenarioReporter reporter) {
    this.spaceshipApi = spaceshipApi;
    this.headquarters = headquarters;
    this.reporter = reporter;
  }

  public void accept(Scenario scenario) {
    reporter.reportScenarioStarted(scenario);
    try {
      acceptScenario(scenario);
      reporter.reportScenarioSucceeded(scenario);
    } catch (ScenarioFailedException e) {
      reporter.reportScenarioFailed(scenario);
      throw e;
    }
  }

  private void acceptScenario(Scenario scenario) {
    headquarters.purgeEvents();
    scenario.events.forEach(this::sendEvent);
    scenario.results.forEach(this::ensureResultIsSatisfied);
  }

  private void sendEvent(InputEvent event) {
    try {
      spaceshipApi.sendEvent(event);
      reporter.reportEventSent(event);
    } catch (EventNotSentException ignored) {
      reporter.reportEventFailed(event);
      throw new ScenarioFailedException();
    }
  }

  private void ensureResultIsSatisfied(Result result) {
    if (anUnexpectedEventWasRecorded(result.event)) {
      reporter.reportResultUnsatisfied(result);
      throw new ScenarioFailedException();
    }

    if (theEventShouldNotHaveBeenRecorded(result)) {
      reporter.reportResultUnsatisfied(result);
      throw new ScenarioFailedException();
    }

    if (expectedEventWasNotRecorded(result)) {
      reporter.reportResultUnsatisfied(result);
      throw new ScenarioFailedException();
    }

    reporter.reportResultSatisfied(result);
  }

  private boolean theEventShouldNotHaveBeenRecorded(Result result) {
    return result.never && headquarters.wasEventRecorded(result.event);
  }

  private boolean anUnexpectedEventWasRecorded(Event event) {
    return event == null && headquarters.hasRecordedAnyEvent();
  }

  private boolean expectedEventWasNotRecorded(Result result) {
    return result.event != null && !result.never && !headquarters.wasEventRecorded(result.event);
  }
}
