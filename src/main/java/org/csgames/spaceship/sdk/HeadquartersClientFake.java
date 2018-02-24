package org.csgames.spaceship.sdk;

class HeadquartersClientFake implements HeadquartersClient {

  private final String token;

  HeadquartersClientFake(String token) {
    this.token = token;
  }

  @Override
  public void sendCommand(Command command) {
    System.out.println(String.format("\uD83D\uDC27 >> %s has just sent a new command!", token));
    System.out.println(String.format("\uD83D\uDC27 >> Here's what they want to do: %s", command));
  }
}
