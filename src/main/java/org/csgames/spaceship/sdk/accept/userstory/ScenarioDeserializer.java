package org.csgames.spaceship.sdk.accept.userstory;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public class ScenarioDeserializer implements JsonDeserializer<Scenario> {

  private final Gson gson;

  public ScenarioDeserializer() {
    gson = new Gson();
  }

  @Override
  public Scenario deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject userStoryJson = json.getAsJsonObject();
    String name = getName(userStoryJson);
    List<InputEvent> events = getEvents(userStoryJson);
    List<Result> results = getResults(userStoryJson);
    return new Scenario(name, events, results);
  }

  private String getName(JsonObject userStory) {
    return userStory.get("name").getAsString();
  }

  private List<InputEvent> getEvents(JsonObject json) {
    return asStream(json.get("events"))
      .map(this::toEvent)
      .collect(toList());
  }

  private List<Result> getResults(JsonObject json) {
    return asStream(json.get("results"))
      .map(this::toResult)
      .collect(toList());
  }

  private Stream<JsonElement> asStream(JsonElement scenarios) {
    Spliterator<JsonElement> scenarioIt = scenarios.getAsJsonArray().spliterator();
    return StreamSupport.stream(scenarioIt, true);
  }

  private InputEvent toEvent(JsonElement jsonElement) {
    String eventPath = jsonElement.getAsString();
    try (InputStream inputStream = getClass().getResourceAsStream(String.format("/events/%s.json", eventPath))) {
      InputStreamReader reader = new InputStreamReader(inputStream);
      return gson.fromJson(reader, InputEvent.class);
    } catch (IOException e) {
      throw new JsonParseException(e);
    }
  }

  private Result toResult(JsonElement jsonElement) {
    String resultPath = jsonElement.getAsString();
    try (InputStream inputStream = getClass().getResourceAsStream(String.format("/results/%s.json", resultPath))) {
      InputStreamReader reader = new InputStreamReader(inputStream);
      return gson.fromJson(reader, Result.class);
    } catch (IOException e) {
      throw new JsonParseException(e);
    }
  }
}
