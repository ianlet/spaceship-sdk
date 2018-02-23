package org.csgames.spaceship.sdk;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class HeadquartersClientFactoryTest {

  private static final String A_TOKEN = "";
  private static final String CLIENT_TYPE_FAKE = HeadquartersClientType.FAKE.name();
  private HeadquartersClientFactory headquartersClientFactory;

  @Before
  public void setUp() throws Exception {
    headquartersClientFactory = new HeadquartersClientFactory();
  }

  @Test
  public void givenNoClientType_thenCreateFakeHeadquartersClient() {
    HeadquartersClient spaceshipClient = headquartersClientFactory.create(A_TOKEN);

    assertThat(spaceshipClient).isInstanceOf(HeadquartersClientFake.class);
  }

  @Test
  public void givenClientTypeFake_thenCreateFakeHeadquartersClient() {
    System.setProperty("clientType", CLIENT_TYPE_FAKE);

    HeadquartersClient headquartersClient = headquartersClientFactory.create(A_TOKEN);

    assertThat(headquartersClient).isInstanceOf(HeadquartersClientFake.class);
  }
}
