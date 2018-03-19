package org.csgames.spaceship.sdk;

public class TemperatureSensorNotWorkingException extends Throwable {

  public TemperatureSensorNotWorkingException(int roomNumber) {
    super(String.format("Room %d sensor is not working!", roomNumber));
  }
}
