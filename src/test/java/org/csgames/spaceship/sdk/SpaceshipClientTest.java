package org.csgames.spaceship.sdk;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class SpaceshipClientTest {

  private static final String A_TOKEN = "";
  private static final String CLIENT_TYPE_FAKE = ClientType.FAKE.name();

  @Test
  public void givenNoClientType_thenRegisterFakeSpaceshipClient() {
    SpaceshipClient spaceshipClient = SpaceshipClient.register(A_TOKEN);

    assertThat(spaceshipClient).isInstanceOf(SpaceshipClientFake.class);
  }

  @Test
  public void givenClientTypeFake_thenRegisterFakeSpaceshipClient() {
    System.setProperty("clientType", CLIENT_TYPE_FAKE);

    SpaceshipClient spaceshipClient = SpaceshipClient.register(A_TOKEN);

    assertThat(spaceshipClient).isInstanceOf(SpaceshipClientFake.class);
  }
}
