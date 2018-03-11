package org.csgames.spaceship.sdk.accept;

import org.csgames.spaceship.sdk.Event;
import org.csgames.spaceship.sdk.Headquarters;
import org.csgames.spaceship.sdk.accept.userstory.Result;
import org.csgames.spaceship.sdk.accept.userstory.Scenario;

public class ScenarioRunner {

  private final SpaceshipApi spaceshipApi;
  private final Headquarters headquarters;

  public ScenarioRunner(SpaceshipApi spaceshipApi, Headquarters headquarters) {
    this.spaceshipApi = spaceshipApi;
    this.headquarters = headquarters;
  }

  public void accept(Scenario scenario) {
    headquarters.purgeEvents();
    scenario.events.forEach(spaceshipApi::sendEvent);
    scenario.results.forEach(this::ensureResultIsSatisfied);
  }

  private void ensureResultIsSatisfied(Result result) {
    if (anUnexpectedEventWasRecorded(result.event)) {
      throw new ScenarioFailedException();
    }

    if (expectedEventWasNotRecorded(result.event)) {
      throw new ScenarioFailedException();
    }
  }

  private boolean anUnexpectedEventWasRecorded(Event event) {
    return event == null && headquarters.hasRecordedAnyEvent();
  }

  private boolean expectedEventWasNotRecorded(Event event) {
    return event != null && !headquarters.wasEventRecorded(event);
  }
}
