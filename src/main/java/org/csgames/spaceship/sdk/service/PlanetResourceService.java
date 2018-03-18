package org.csgames.spaceship.sdk.service;

import org.csgames.spaceship.sdk.Event;
import org.csgames.spaceship.sdk.EventFactory;
import org.csgames.spaceship.sdk.Headquarters;

import static org.csgames.spaceship.sdk.EventType.RESOURCE_REGISTERED;

public class PlanetResourceService {

  private final EventFactory eventFactory;
  private final Headquarters headquarters;

  public PlanetResourceService(EventFactory eventFactory, Headquarters headquarters) {
    this.eventFactory = eventFactory;
    this.headquarters = headquarters;
  }

  public void registerResource(PlanetRegistry planetRegistry, String resource) {
    Event event = eventFactory.create(RESOURCE_REGISTERED, planetRegistry.toString(), resource);
    headquarters.recordEvent(event);
  }
}
