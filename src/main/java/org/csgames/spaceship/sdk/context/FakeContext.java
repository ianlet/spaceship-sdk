package org.csgames.spaceship.sdk.context;

import org.csgames.spaceship.sdk.Headquarters;
import org.csgames.spaceship.sdk.HeadquartersFake;
import org.csgames.spaceship.sdk.accept.result.UserStoryResultFactory;
import org.csgames.spaceship.sdk.accept.result.UserStoryResultStore;
import org.csgames.spaceship.sdk.accept.result.UserStoryResultStoreFake;
import org.csgames.spaceship.sdk.accept.userstory.UserStoryRepository;
import org.csgames.spaceship.sdk.accept.userstory.UserStoryRepositoryJsonFile;

import java.time.Clock;

import static org.csgames.spaceship.sdk.context.ServiceLocator.register;

public class FakeContext implements Context {

  private final String token;

  public FakeContext(String token) {
    this.token = token;
  }

  @Override
  public void apply() {
    Headquarters headquarters = new HeadquartersFake(token);
    register(Headquarters.class, headquarters);

    UserStoryRepository userStoryRepository = new UserStoryRepositoryJsonFile();
    register(UserStoryRepository.class, userStoryRepository);

    UserStoryResultFactory userStoryResultFactory = new UserStoryResultFactory(Clock.systemUTC());
    register(UserStoryResultFactory.class, userStoryResultFactory);

    UserStoryResultStore userStoryResultStore = new UserStoryResultStoreFake();
    register(UserStoryResultStore.class, userStoryResultStore);
  }
}
