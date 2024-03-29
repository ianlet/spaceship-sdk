package org.csgames.spaceship.sdk;

public class HeadquartersFake implements Headquarters {

  private final String token;

  public HeadquartersFake(String token) {
    this.token = token;
  }

  @Override
  public void recordEvent(Event event) {
    System.out.println(String.format("\uD83D\uDC27 >> New event recorded from %s!", token));
    System.out.println(String.format("\uD83D\uDC27 >> Here's what they just did: %s", event));
  }

  @Override
  public boolean wasEventRecorded(Event event) {
    return true;
  }

  @Override
  public boolean hasRecordedAnyEvent() {
    return true;
  }

  @Override
  public void purgeEvents() {
  }
}
