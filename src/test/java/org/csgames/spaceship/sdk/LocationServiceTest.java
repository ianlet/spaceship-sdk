package org.csgames.spaceship.sdk;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class LocationServiceTest {

  private static final int DISTANCE_FROM_LONDON_TO_ARLINGTON = 5924814;
  private static final Coordinates SOME_COORDINATES = new Coordinates(11.21D, 59.13D);
  private static final Coordinates LONDON_COORDINATES = new Coordinates(51.5D, 0D);
  private static final Coordinates ARLINGTON_COORDINATES = new Coordinates(38.8D, -77.1D);

  private LocationService locationService;

  @Before
  public void setUp() throws Exception {
    locationService = new LocationService();
  }

  @Test
  public void givenIdenticalCoordinates_thenDistanceShouldBeZero() {
    int distance = locationService.distanceBetween(SOME_COORDINATES, SOME_COORDINATES);

    assertThat(distance).isEqualTo(0);
  }

  @Test
  public void givenDifferentCoordinates_thenCalculateDistanceBetweenTheCoordinates() {
    int distance = locationService.distanceBetween(LONDON_COORDINATES, ARLINGTON_COORDINATES);

    assertThat(distance).isEqualTo(DISTANCE_FROM_LONDON_TO_ARLINGTON);
  }

  @Test
  public void calculateDirectionFromOneCoordinatesToAnother() {
    CoordinatesTable table = new CoordinatesTable();
    table.push(new Coordinates(0, 0), new Coordinates(5, 0), Direction.NORTH);
    table.push(new Coordinates(0, 0), new Coordinates(-5, 0), Direction.SOUTH);
    table.push(new Coordinates(0, 0), new Coordinates(0, 5), Direction.EAST);
    table.push(new Coordinates(0, 0), new Coordinates(0, -5), Direction.WEST);
    table.push(new Coordinates(0, 0), new Coordinates(5, 5), Direction.NORTH_EAST);
    table.push(new Coordinates(0, 0), new Coordinates(-5, 5), Direction.SOUTH_EAST);
    table.push(new Coordinates(0, 0), new Coordinates(-5, -5), Direction.SOUTH_WEST);
    table.push(new Coordinates(0, 0), new Coordinates(5, -5), Direction.NORTH_WEST);

    table.forEach((from, to) -> locationService.directionTo(from, to));
  }
}
