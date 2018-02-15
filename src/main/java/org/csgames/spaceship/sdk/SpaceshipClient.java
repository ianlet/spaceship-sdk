package org.csgames.spaceship.sdk;

public abstract class SpaceshipClient {

  public static SpaceshipClient register(String token) {
    ClientType clientType = ClientType.fromString(System.getProperty("clientType"));
    switch (clientType) {
      case FAKE:
      default:
        return new SpaceshipClientFake(token);
    }
  }

  protected abstract void sendCommand(Command command);
}
