package org.csgames.spaceship.sdk;

public class TemperatureReader {

  private int[] temperatureReadCount = new int[20];

  public double readRoomTemperature(int roomNumber) throws TemperatureSensorNotWorkingException {
    int count;
    switch (roomNumber) {
      case 0:
        return toP(15d);
      case 1:
        count = temperatureReadCount[roomNumber];
        temperatureReadCount[roomNumber]++;
        if (count % 2 == 0) {
          return 0d;
        } else {
          return -50d;
        }
      case 2:
        return toP(-15d);
      case 3:
        return toP(10d);
      case 4:
        throw new TemperatureSensorNotWorkingException(roomNumber);
      case 5:
        return 5d;
      case 6:
        return toP(-5d);
      case 7:
        count = temperatureReadCount[roomNumber];
        temperatureReadCount[roomNumber]++;
        if (count % 2 == 0) {
          return toP(10d);
        } else {
          return toP(-50d);
        }
      case 8:
        return -23d;
      case 9:
        throw new TemperatureSensorNotWorkingException(roomNumber);
      default:
        throw new UnknownRoomException(roomNumber);
    }
  }

  private double toP(double celsius) {
    return -(celsius * 9 / 5 + 32);
  }

  public double readMeanHabitableTemperature() {
    return 5d;
  }
}
