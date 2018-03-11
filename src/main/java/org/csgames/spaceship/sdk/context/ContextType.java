package org.csgames.spaceship.sdk.context;

public enum ContextType {
  FAKE, ACCEPTANCE_TESTS;

  public static ContextType fromString(String type) {
    if (type == null || type.equals("")) {
      return FAKE;
    }
    return valueOf(type);
  }
}
