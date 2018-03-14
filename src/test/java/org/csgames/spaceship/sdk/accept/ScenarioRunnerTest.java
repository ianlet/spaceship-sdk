package org.csgames.spaceship.sdk.accept;

import org.csgames.spaceship.sdk.Event;
import org.csgames.spaceship.sdk.EventFactory;
import org.csgames.spaceship.sdk.EventType;
import org.csgames.spaceship.sdk.Headquarters;
import org.csgames.spaceship.sdk.accept.userstory.InputEvent;
import org.csgames.spaceship.sdk.accept.userstory.Result;
import org.csgames.spaceship.sdk.accept.userstory.Scenario;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

public class ScenarioRunnerTest {

  private static final String A_SCENARIO = "scenario";
  private static final String A_RESULT = "result";
  private static final String A_TEAM = "team-01";
  private static final String A_PAYLOAD = "23";
  private static final EventType AN_EVENT_TYPE = EventType.FISH_CAUGHT;
  private static final Event NO_EVENT = null;

  private final InputEvent anInputEvent = new InputEvent("an event", "event type", "source", "payload");
  private final InputEvent firstInputEvent = new InputEvent("first event", "event type", "source", "payload");
  private final InputEvent secondInputEvent = new InputEvent("second event", "event type", "source", "payload");

  private final Result aResult = new Result("result", null);

  private SpaceshipApi spaceshipApi;
  private EventFactory eventFactory;

  private ScenarioRunner scenarioRunner;
  private Headquarters headquarters;

  @Before
  public void setUp() throws Exception {
    eventFactory = new EventFactory();

    spaceshipApi = mock(SpaceshipApi.class);
    headquarters = mock(Headquarters.class);
    willReturn(true).given(headquarters).wasEventRecorded(any());
    ScenarioReporter reporter = mock(ScenarioReporter.class);

    scenarioRunner = new ScenarioRunner(spaceshipApi, headquarters, reporter);
  }

  @Test
  public void itShouldSendInputEventsInOrderToTheSpaceshipApi() {
    Scenario scenario = new Scenario(A_SCENARIO, asList(firstInputEvent, secondInputEvent), emptyList());

    scenarioRunner.accept(scenario);

    InOrder inOrder = inOrder(spaceshipApi);
    inOrder.verify(spaceshipApi).sendEvent(firstInputEvent);
    inOrder.verify(spaceshipApi).sendEvent(secondInputEvent);
  }

  @Test(expected = ScenarioFailedException.class)
  public void itShouldFailTheScenario_givenNoExpectedEventWereRecorded() {
    Result result = givenResultExpectingAnEventNotRecorded();
    Scenario scenario = new Scenario(A_SCENARIO, emptyList(), asList(result));

    scenarioRunner.accept(scenario);
  }

  @Test(expected = ScenarioFailedException.class)
  public void itShouldFailTheScenario_givenAnUnexpectedEventWasRecorded() {
    givenAnEventWasRecorded();
    Result result = givenResultExpectingNoEvent();
    Scenario scenario = new Scenario(A_SCENARIO, emptyList(), asList(result));

    scenarioRunner.accept(scenario);
  }

  @Test
  public void itShouldNotFailTheScenario_givenNoUnexpectedEventWereRecorded() {
    givenNoEventRecorded();
    Result result = givenResultExpectingNoEvent();
    Scenario scenario = new Scenario(A_SCENARIO, emptyList(), asList(result));

    scenarioRunner.accept(scenario);
  }

  @Test
  public void itShouldPurgeHeadquartersEventsBeforeAcceptingTheScenario() {
    Scenario scenario = new Scenario(A_SCENARIO, asList(anInputEvent), asList(aResult));

    scenarioRunner.accept(scenario);

    InOrder inOrder = inOrder(headquarters, spaceshipApi);
    inOrder.verify(headquarters).purgeEvents();
    inOrder.verify(spaceshipApi).sendEvent(anInputEvent);
  }

  private Result givenResultExpectingAnEventNotRecorded() {
    Event unexpectedEvent = eventFactory.create(AN_EVENT_TYPE, A_TEAM, A_PAYLOAD);
    willReturn(false).given(headquarters).wasEventRecorded(unexpectedEvent);
    return new Result(A_RESULT, unexpectedEvent);
  }

  private void givenAnEventWasRecorded() {
    willReturn(true).given(headquarters).hasRecordedAnyEvent();
  }

  private Result givenResultExpectingNoEvent() {
    return new Result(A_RESULT, NO_EVENT);
  }

  private void givenNoEventRecorded() {
    willReturn(false).given(headquarters).hasRecordedAnyEvent();
  }
}
