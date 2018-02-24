package org.csgames.spaceship.sdk;

class HeadquartersClientFactory {

  Headquarters create(String token) {
    HeadquartersType clientType = HeadquartersType.fromString(System.getProperty("clientType"));
    switch (clientType) {
      case FAKE:
      default:
        return new HeadquartersFake(token);
    }
  }
}
