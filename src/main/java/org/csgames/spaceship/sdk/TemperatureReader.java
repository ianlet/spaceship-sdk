package org.csgames.spaceship.sdk;

public class TemperatureReader {

  private int[] temperatureReadCount = new int[20];

  public double readRoomTemperature(int roomNumber) throws TemperatureSensorNotWorkingException {
    int count;
    switch (roomNumber) {
      case 0:
        return 0d;
      case 1:
        count = temperatureReadCount[roomNumber];
        temperatureReadCount[roomNumber]++;
        if (count % 2 == 0) {
          return 0d;
        } else {
          return -50d;
        }
      case 2:
        return -15;
      case 3:
        return -5d;
      case 4:
        throw new TemperatureSensorNotWorkingException(roomNumber);
      case 5:
        return 5d;
      case 6:
        return -14d;
      case 7:
        count = temperatureReadCount[roomNumber];
        temperatureReadCount[roomNumber]++;
        if (count % 2 == 0) {
          return 1d;
        } else {
          return -50d;
        }
      case 8:
        return -23d;
      case 9:
        throw new TemperatureSensorNotWorkingException(roomNumber);
      default:
        throw new UnknownRoomException(roomNumber);
    }
  }

  public double readMeanHabitableTemperature() {
    return 5d;
  }
}
