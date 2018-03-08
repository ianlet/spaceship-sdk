package org.csgames.spaceship.sdk.accept.userstory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class UserStoryRepositoryJsonFile implements UserStoryRepository {

  @Override
  public List<UserStory> findAll() {
    Gson gson = new GsonBuilder()
      .registerTypeAdapter(UserStory.class, new UserStoryDeserializer())
      .create();

    try (InputStream inputStream = getClass().getResourceAsStream("/user_stories/send_supplies_to_research_team.json")) {
      InputStreamReader reader = new InputStreamReader(inputStream);
      UserStory userStory = gson.fromJson(reader, UserStory.class);
      return asList(userStory);
    } catch (IOException e) {
      return emptyList();
    }
  }
}
