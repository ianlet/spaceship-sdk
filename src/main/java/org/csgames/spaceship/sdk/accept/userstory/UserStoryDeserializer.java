package org.csgames.spaceship.sdk.accept.userstory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

public class UserStoryDeserializer implements JsonDeserializer<UserStory> {

  private final Gson gson;

  public UserStoryDeserializer() {
    this.gson = new GsonBuilder().registerTypeAdapter(Scenario.class, new ScenarioDeserializer()).create();
  }

  @Override
  public UserStory deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject userStoryJson = json.getAsJsonObject();
    String name = getName(userStoryJson);
    List<Scenario> scenarios = getScenarios(userStoryJson);
    int points = getPoints(userStoryJson);
    int penalties = getPenalties(userStoryJson);
    int deaths = getDeaths(userStoryJson);

    return new UserStory(name, scenarios, points, penalties, deaths);
  }

  private String getName(JsonObject userStory) {
    return userStory.get("name").getAsString();
  }

  private int getPoints(JsonObject userStory) {
    return userStory.get("points").getAsInt();
  }

  private int getPenalties(JsonObject userStory) {
    return userStory.get("penalties").getAsInt();
  }

  private int getDeaths(JsonObject userStory) {
    return userStory.get("deaths").getAsInt();
  }

  private List<Scenario> getScenarios(JsonObject json) {
    return asStream(json.get("scenarios"))
      .map(this::toScenario)
      .collect(toList());
  }

  private Stream<JsonElement> asStream(JsonElement scenarios) {
    Spliterator<JsonElement> scenarioIt = scenarios.getAsJsonArray().spliterator();
    return StreamSupport.stream(scenarioIt, true);
  }

  private Scenario toScenario(JsonElement jsonElement) {
    String scenarioPath = jsonElement.getAsString();
    try (InputStream inputStream = getClass().getResourceAsStream(String.format("/scenarios/%s.json", scenarioPath))) {
      InputStreamReader reader = new InputStreamReader(inputStream);
      return gson.fromJson(reader, Scenario.class);
    } catch (IOException e) {
      throw new JsonParseException(e);
    }
  }
}
