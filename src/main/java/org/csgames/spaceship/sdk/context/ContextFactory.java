package org.csgames.spaceship.sdk.context;

public class ContextFactory {

  public Context create(String token) {
    ContextType headquartersType = ContextType.fromString(System.getProperty("context"));

    switch (headquartersType) {
      case ACCEPTANCE_TESTS:
        return new AcceptanceTestContext(token);
      case FAKE:
      default:
        return new FakeContext(token);
    }
  }
}
