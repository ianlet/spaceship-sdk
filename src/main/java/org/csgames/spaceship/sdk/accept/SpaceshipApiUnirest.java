package org.csgames.spaceship.sdk.accept;

import com.google.gson.Gson;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.csgames.spaceship.sdk.accept.userstory.InputEvent;

public class SpaceshipApiUnirest implements SpaceshipApi {

  private static final String API_HOST = "http://localhost";

  private final int port;
  private final Gson gson;

  public SpaceshipApiUnirest(int port, Gson gson) {
    this.port = port;
    this.gson = gson;
  }

  @Override
  public void sendEvent(InputEvent event) {
    try {
      Unirest
        .post(String.format("%s:%s/events", API_HOST, port))
        .header("accept", "application/json")
        .body(gson.toJson(event))
        .asJson();
    } catch (UnirestException e) {
      e.printStackTrace();
    }
  }
}
