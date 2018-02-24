package org.csgames.spaceship.sdk;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class HeadquartersFactoryTest {

  private static final String A_TOKEN = "";
  private static final String CLIENT_TYPE_FAKE = HeadquartersType.FAKE.name();
  private HeadquartersClientFactory headquartersClientFactory;

  @Before
  public void setUp() throws Exception {
    headquartersClientFactory = new HeadquartersClientFactory();
  }

  @Test
  public void givenNoClientType_thenCreateFakeHeadquartersClient() {
    Headquarters spaceshipClient = headquartersClientFactory.create(A_TOKEN);

    assertThat(spaceshipClient).isInstanceOf(HeadquartersFake.class);
  }

  @Test
  public void givenClientTypeFake_thenCreateFakeHeadquartersClient() {
    System.setProperty("clientType", CLIENT_TYPE_FAKE);

    Headquarters headquarters = headquartersClientFactory.create(A_TOKEN);

    assertThat(headquarters).isInstanceOf(HeadquartersFake.class);
  }
}