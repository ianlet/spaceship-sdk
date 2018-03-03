package org.csgames.spaceship.sdk;

class HeadquartersFactory {

  Headquarters create(String token) {
    HeadquartersType headquartersType = HeadquartersType.fromString(System.getProperty("headquartersType"));
    switch (headquartersType) {
      case FAKE:
      default:
        return new HeadquartersFake(token);
    }
  }
}
