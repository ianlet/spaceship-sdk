package org.csgames.spaceship.sdk;

import org.csgames.spaceship.sdk.CoordinatesTable.Coordinates;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class LocationServiceTest {

  private static final double TOLERANCE = 1D;

  private static final double A_LATITUDE = 10.21;
  private static final double A_LONGITUDE = 59.13;

  private static final double LONDON_LATITUDE = 51.5;
  private static final double LONDON_LONGITUDE = 0;
  private static final double ARLINGTON_LATITUDE = 38.8;
  private static final double ARLINGTON_LONGITUDE = -77.1;

  private static final int DISTANCE_FROM_LONDON_TO_ARLINGTON = 5924814;

  private LocationService locationService;

  @Before
  public void setUp() throws Exception {
    locationService = new LocationService();
  }

  @Test
  public void givenIdenticalCoordinates_thenDistanceShouldBeZero() {
    int distance = locationService.distanceBetween(A_LATITUDE, A_LONGITUDE, A_LATITUDE, A_LONGITUDE);

    assertThat(distance).isEqualTo(0);
  }

  @Test
  public void givenDifferentCoordinates_thenCalculateDistanceBetweenTheCoordinates() {
    int distance = locationService.distanceBetween(LONDON_LATITUDE, LONDON_LONGITUDE, ARLINGTON_LATITUDE, ARLINGTON_LONGITUDE);

    assertThat(distance).isEqualTo(DISTANCE_FROM_LONDON_TO_ARLINGTON);
  }

  @Test
  public void calculateDirectionFromOneCoordinatesToAnother() {
    CoordinatesTable table = new CoordinatesTable();
    table.push(new Coordinates(0, 0), new Coordinates(5, 0), "N");
    table.push(new Coordinates(0, 0), new Coordinates(-5, 0), "S");
    table.push(new Coordinates(0, 0), new Coordinates(0, 5), "E");
    table.push(new Coordinates(0, 0), new Coordinates(0, -5), "W");
    table.push(new Coordinates(0, 0), new Coordinates(5, 5), "NE");
    table.push(new Coordinates(0, 0), new Coordinates(-5, 5), "SE");
    table.push(new Coordinates(0, 0), new Coordinates(-5, -5), "SW");
    table.push(new Coordinates(0, 0), new Coordinates(5, -5), "NW");

    table.forEach((from, to) -> locationService.directionTo(from.latitude, from.longitude, to.latitude, to.longitude));
  }
}
