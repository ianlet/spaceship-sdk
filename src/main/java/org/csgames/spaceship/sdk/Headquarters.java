package org.csgames.spaceship.sdk;

public interface Headquarters {

  void recordEvent(Event event);

  boolean wasEventRecorded(Event event);

  boolean hasRecordedAnyEvent();
}
