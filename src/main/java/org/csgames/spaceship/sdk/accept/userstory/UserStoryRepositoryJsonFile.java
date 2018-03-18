package org.csgames.spaceship.sdk.accept.userstory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class UserStoryRepositoryJsonFile implements UserStoryRepository {

  private static final String USER_STORIES_DIR = "/user_stories";

  private final Gson gson;

  public UserStoryRepositoryJsonFile() {
    gson = new GsonBuilder()
      .registerTypeAdapter(UserStory.class, new UserStoryDeserializer())
      .create();
  }

  @Override
  public List<UserStory> findAll() {
    try {
      List<Path> userStoryPaths = listUserStoryPaths();
      return userStoryPaths.stream()
        .map(Path::toFile)
        .filter(File::isFile)
        .map(this::toFileReader)
        .filter(Objects::nonNull)
        .map(this::toUserStory)
        .collect(toList());
    } catch (IOException | URISyntaxException e) {
      return emptyList();
    }
  }

  private List<Path> listUserStoryPaths() throws URISyntaxException, IOException {
    URI uri = getClass().getResource(USER_STORIES_DIR).toURI();
    Path myPath;
    if (uri.getScheme().equals("jar")) {
      FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
      myPath = fileSystem.getPath(USER_STORIES_DIR);
    } else {
      myPath = Paths.get(uri);
    }
    return Files.walk(myPath, 1).collect(toList());
  }

  private FileReader toFileReader(File file) {
    try {
      return new FileReader(file);
    } catch (FileNotFoundException e) {
      return null;
    }
  }

  private UserStory toUserStory(FileReader reader) {
    return gson.fromJson(reader, UserStory.class);
  }
}
