package org.csgames.spaceship.sdk;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class TemperatureReaderTest {

  private static final int ROOM_01 = 1;
  private static final int ROOM_02 = 2;
  private static final int ROOM_03 = 3;
  private static final int ROOM_04 = 4;

  private static final double ZERO_CELSIUS_DEGREES = 0d;
  private static final double MINUS_FIFTY_CELSIUS_DEGREES = -50d;
  private static final double MINUS_FIFTEEN_CELSIUS_DEGREES = -15d;
  private static final double MINUS_ONE_HUNDRED_CELSIUS_DEGREES = -100d;

  private TemperatureReader temperatureReader;

  @Before
  public void setUp() throws Exception {
    temperatureReader = new TemperatureReader();
  }

  @Test
  public void itShouldReadTemperatureOfZeroCelsiusDegrees_givenRoom01TemperatureReadForFirstTime() throws Throwable {
    double roomTemperature = temperatureReader.readRoomTemperature(ROOM_01);

    assertThat(roomTemperature).isWithin(0).of(ZERO_CELSIUS_DEGREES);
  }

  @Test
  public void itShouldReadTemperatureOfMinusFiftyCelsiusDegrees_givenRoom01TemperatureReadForSecondTime() throws Throwable {
    temperatureReader.readRoomTemperature(ROOM_01);

    double roomTemperature = temperatureReader.readRoomTemperature(ROOM_01);

    assertThat(roomTemperature).isWithin(0).of(MINUS_FIFTY_CELSIUS_DEGREES);
  }

  @Test
  public void itShouldReadTemperatureOfMinusFifteenCelsiusDegrees_givenRoom02TemperatureRead() throws Throwable {
    double roomTemperature = temperatureReader.readRoomTemperature(ROOM_02);

    assertThat(roomTemperature).isWithin(0).of(MINUS_FIFTEEN_CELSIUS_DEGREES);
  }

  @Test
  public void itShouldReadTemperatureOfMinusOneHundredCelsiusDegrees_givenRoom03TemperatureRead() throws Throwable {
    double roomTemperature = temperatureReader.readRoomTemperature(ROOM_03);

    assertThat(roomTemperature).isWithin(0).of(MINUS_ONE_HUNDRED_CELSIUS_DEGREES);
  }

  @Test(expected = TemperatureSensorNotWorkingException.class)
  public void itShouldThrowTemperatureSensorNotWorking_givenRoom04TemperatureRead() throws Throwable {
    temperatureReader.readRoomTemperature(ROOM_04);
  }
}
