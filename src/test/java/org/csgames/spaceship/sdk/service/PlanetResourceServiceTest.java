package org.csgames.spaceship.sdk.service;

import org.csgames.spaceship.sdk.Event;
import org.csgames.spaceship.sdk.EventFactory;
import org.csgames.spaceship.sdk.EventType;
import org.csgames.spaceship.sdk.Headquarters;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PlanetResourceServiceTest {

  private static final PlanetRegistry CLASS_M_PLANET_REGISTRY = PlanetRegistry.CLASS_M;
  private static final String A_RESOURCE = "rock";

  private EventFactory eventFactory;
  private Headquarters headquarters;

  private PlanetResourceService planetResourceService;

  @Before
  public void setUp() throws Exception {
    eventFactory = new EventFactory();
    headquarters = mock(Headquarters.class);

    planetResourceService = new PlanetResourceService(eventFactory, headquarters);
  }

  @Test
  public void itShouldRecordTheRegisteredResource() {
    planetResourceService.registerResource(CLASS_M_PLANET_REGISTRY, A_RESOURCE);

    Event registeredResourceEvent = eventFactory.create(EventType.RESOURCE_REGISTERED, CLASS_M_PLANET_REGISTRY.toString(), A_RESOURCE);
    verify(headquarters).recordEvent(registeredResourceEvent);
  }
}
