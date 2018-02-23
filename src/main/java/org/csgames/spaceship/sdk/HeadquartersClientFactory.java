package org.csgames.spaceship.sdk;

class HeadquartersClientFactory {

  HeadquartersClient create(String token) {
    HeadquartersClientType clientType = HeadquartersClientType.fromString(System.getProperty("clientType"));
    switch (clientType) {
      case FAKE:
      default:
        return new HeadquartersClientFake(token);
    }
  }
}
