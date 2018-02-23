package org.csgames.spaceship.sdk;

class HeadquartersClientFake implements HeadquartersClient {

  private final String token;

  HeadquartersClientFake(String token) {
    this.token = token;
  }
}
