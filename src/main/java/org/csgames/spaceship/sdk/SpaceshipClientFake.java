package org.csgames.spaceship.sdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpaceshipClientFake extends SpaceshipClient {

  private static final Logger logger = LoggerFactory.getLogger(SpaceshipClientFake.class);

  private final String token;

  public SpaceshipClientFake(String token) {
    this.token = token;
  }

  @Override
  protected void sendCommand(Command command) {
    logger.info(String.format("%s is sending command: %s", token, command));
  }
}
