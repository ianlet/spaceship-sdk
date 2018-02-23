package org.csgames.spaceship.sdk;

enum HeadquartersClientType {
  FAKE;

  public static HeadquartersClientType fromString(String clientType) {
    if (clientType == null || clientType.equals("")) {
      return FAKE;
    }
    return valueOf(clientType);
  }
}
