package org.csgames.spaceship.sdk;

class HeadquartersFake implements Headquarters {

  private final String token;

  HeadquartersFake(String token) {
    this.token = token;
  }

  @Override
  public void recordEvent(Event event) {
    System.out.println(String.format("\uD83D\uDC27 >> New event recorded from %s!", token));
    System.out.println(String.format("\uD83D\uDC27 >> Here's what they just did: %s", event));
  }
}
