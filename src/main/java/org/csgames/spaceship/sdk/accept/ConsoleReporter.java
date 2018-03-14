package org.csgames.spaceship.sdk.accept;

import net.andreinc.ansiscape.AnsiClass;
import net.andreinc.ansiscape.AnsiScape;
import net.andreinc.ansiscape.AnsiScapeContext;
import net.andreinc.ansiscape.AnsiSequence;

import org.csgames.spaceship.sdk.accept.userstory.InputEvent;
import org.csgames.spaceship.sdk.accept.userstory.Result;
import org.csgames.spaceship.sdk.accept.userstory.Scenario;
import org.csgames.spaceship.sdk.accept.userstory.UserStory;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConsoleReporter implements UserStoryReporter, ScenarioReporter {

  private final AnsiScape c;
  private UserStoryDto userStory;
  private ScenarioDto currentScenario;

  public ConsoleReporter() {
    AnsiClass label = AnsiClass.withName("label").add(AnsiSequence.BOLD, AnsiSequence.YELLOW);
    AnsiClass value = AnsiClass.withName("value").add(AnsiSequence.RESET);
    AnsiClass success = AnsiClass.withName("success").add(AnsiSequence.RESET, AnsiSequence.BOLD, AnsiSequence.GREEN);
    AnsiClass failure = AnsiClass.withName("failure").add(AnsiSequence.RESET, AnsiSequence.BOLD, AnsiSequence.RED);
    AnsiClass pending = AnsiClass.withName("pending").add(AnsiSequence.RESET, AnsiSequence.OVERSTRIKE, AnsiSequence.DIM);

    AnsiScapeContext context = new AnsiScapeContext();
    context.add(label).add(value).add(success).add(failure).add(pending);

    c = new AnsiScape(context);
  }

  @Override
  public void reportUserStoryStarted(UserStory userStory) {
    this.userStory = new UserStoryDto(userStory);
  }

  @Override
  public void reportUserStoryFailed(UserStory userStory) {
    this.userStory.status = "failure";
    this.userStory.print(c);
  }

  @Override
  public void reportUserStorySucceeded(UserStory userStory) {
    this.userStory.status = "success";
    this.userStory.print(c);
  }

  @Override
  public void reportScenarioStarted(Scenario scenario) {
    currentScenario = userStory.scenarios.get(scenario.name);
  }

  @Override
  public void reportScenarioFailed(Scenario scenario) {
    userStory.scenarios.get(scenario.name).status = "failure";
  }

  @Override
  public void reportScenarioSucceeded(Scenario scenario) {
    userStory.scenarios.get(scenario.name).status = "success";
  }

  @Override
  public void reportEventSent(InputEvent event) {
    currentScenario.steps.get(event.name).status = "success";
  }

  @Override
  public void reportResultUnsatisfied(Result result) {
    currentScenario.steps.get(result.name).status = "failure";
  }

  @Override
  public void reportResultSatisfied(Result result) {
    currentScenario.steps.get(result.name).status = "success";
  }

  private class UserStoryDto {

    String name;
    Map<String, ScenarioDto> scenarios;
    String status = "pending";

    UserStoryDto(UserStory userStory) {
      this.name = userStory.name;
      this.scenarios = new LinkedHashMap<>();
      userStory.scenarios.forEach(scenario -> this.scenarios.put(scenario.name, new ScenarioDto(scenario)));
    }

    void print(AnsiScape c) {
      String info = (status.equals("success")) ? "" : "[{failure FAILED}]";
      String out = c.format(String.format("{%s {label User Story%s: {value %s}}}", status, info, name));
      System.out.println(out);
      scenarios.values().forEach(scenario -> scenario.print(c));
      System.out.println("\n");
    }
  }

  private class ScenarioDto {

    String name;
    Map<String, StepDto> steps;
    String status = "pending";

    ScenarioDto(Scenario scenario) {
      this.name = scenario.name;
      this.steps = new LinkedHashMap<>();
      scenario.events.forEach(event -> this.steps.put(event.name, new StepDto(event.name)));
      scenario.results.forEach(result -> this.steps.put(result.name, new StepDto(result.name)));
    }

    void print(AnsiScape c) {
      String info = (status.equals("success")) ? "" : "[{failure FAILED}]";
      String out = c.format(String.format("{%s   {label Scenario%s: {value %s}}}", status, info, name));
      System.out.println(out);

      steps.values().forEach(step -> step.print(c));
    }
  }

  private class StepDto {

    String name;
    String status = "pending";

    StepDto(String name) {
      this.name = name;
    }

    void print(AnsiScape c) {
      String icon = (status.equals("failure")) ? "✕" : " ";
      String out = c.format(String.format("{%s %s   %s}", status, icon, name));
      System.out.println(out);
    }
  }
}
