package org.csgames.spaceship.sdk.accept.userstory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
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

import static java.nio.file.Files.isRegularFile;
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
        .map(this::toInputStream)
        .filter(Objects::nonNull)
        .map(this::toReader)
        .map(this::toUserStory)
        .collect(toList());
    } catch (IOException | URISyntaxException e) {
      return emptyList();
    }
  }

  private List<Path> listUserStoryPaths() throws URISyntaxException, IOException {
    Path userStoryDir = getUserStoryDir();
    return Files.walk(userStoryDir, 1)
      .filter(path -> isRegularFile(path))
      .collect(toList());
  }

  private Path getUserStoryDir() throws IOException, URISyntaxException {
    URI uri = getClass().getResource(USER_STORIES_DIR).toURI();
    if (isJar(uri)) {
      FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
      return fileSystem.getPath(USER_STORIES_DIR);
    } else {
      return Paths.get(uri);
    }
  }

  private boolean isJar(URI uri) {
    return uri.getScheme().equals("jar");
  }

  private InputStream toInputStream(Path path) {
    try {
      return Files.newInputStream(path);
    } catch (IOException e) {
      return null;
    }
  }

  private InputStreamReader toReader(InputStream inputStream) {
    return new InputStreamReader(inputStream);
  }

  private UserStory toUserStory(Reader reader) {
    return gson.fromJson(reader, UserStory.class);
  }
}
