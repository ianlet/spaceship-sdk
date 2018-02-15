package org.csgames.spaceship.sdk;

enum ClientType {
  FAKE;

  public static ClientType fromString(String clientType) {
    if (clientType == null || clientType.equals("")) {
      return FAKE;
    }
    return valueOf(clientType);
  }
}
