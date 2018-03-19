package org.csgames.spaceship.sdk;

public class TemperatureReader {

  private int[] temperatureReadCount = new int[4];

  public double readRoomTemperature(int roomNumber) throws TemperatureSensorNotWorkingException {
    switch (roomNumber) {
      case 0:
        return 0d;
      case 1:
        int count = temperatureReadCount[roomNumber];
        temperatureReadCount[roomNumber]++;
        if (count % 2 == 0) {
          return 0d;
        } else {
          return -50d;
        }
      case 2:
        return -15;
      case 3:
        return -100;
      case 4:
        throw new TemperatureSensorNotWorkingException(roomNumber);
      default:
        throw new UnknownRoomException(roomNumber);
    }
  }

  public double readMeanHabitableTemperature() {
    return -7.5d;
  }
}
