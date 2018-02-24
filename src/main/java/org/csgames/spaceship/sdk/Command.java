package org.csgames.spaceship.sdk;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
class Command {

  private final CommandType type;
  private final String target;
  private final String payload;

  Command(CommandType type, String target, String payload) {
    this.type = type;
    this.target = target;
    this.payload = payload;
  }
}
