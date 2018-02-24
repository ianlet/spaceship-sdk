package org.csgames.spaceship.sdk;

enum HeadquartersType {
  FAKE;

  public static HeadquartersType fromString(String type) {
    if (type == null || type.equals("")) {
      return FAKE;
    }
    return valueOf(type);
  }
}
