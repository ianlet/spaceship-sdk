package org.csgames.spaceship.sdk.context;

import java.util.HashMap;
import java.util.Map;

public enum ServiceLocator {
  LOCATOR;

  private final Map<Class<?>, Object> services = new HashMap<>();

  public static <T> void register(Class<T> key, T service) {
    LOCATOR.registerService(key, service);
  }

  @SuppressWarnings("unchecked")
  public static <T> T locate(Class<T> serviceKey) {
    return LOCATOR.locateService(serviceKey);
  }

  private <T> void registerService(Class<T> key, T service) {
    if (services.containsKey(key)) {
      throw new IllegalArgumentException(key + " is already registered");
    }
    services.put(key, service);
  }

  private <T> T locateService(Class<T> serviceKey) {
    if (!services.containsKey(serviceKey)) {
      throw new IllegalStateException(serviceKey + " is not registered");
    }
    return (T) services.get(serviceKey);

  }
}
