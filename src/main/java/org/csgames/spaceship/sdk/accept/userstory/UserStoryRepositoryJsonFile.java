package org.csgames.spaceship.sdk.accept.userstory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class UserStoryRepositoryJsonFile implements UserStoryRepository {

  private final Gson gson;

  public UserStoryRepositoryJsonFile() {
    gson = new GsonBuilder()
      .registerTypeAdapter(UserStory.class, new UserStoryDeserializer())
      .create();
  }

  @Override
  public List<UserStory> findAll() {

    try (InputStream inputStream = getClass().getResourceAsStream("/user_stories/F1-track_away_team_location.json")) {
      InputStreamReader reader = new InputStreamReader(inputStream);
      UserStory userStory = gson.fromJson(reader, UserStory.class);
      return asList(userStory);
    } catch (IOException e) {
      return emptyList();
    }
  }
}
