package me.jnpmarques;

import org.junit.Test;

import me.jnpmarques.MySourceConnectorConfig;

public class MySourceConnectorConfigTest {
  @Test
  public void doc() {
    System.out.println(MySourceConnectorConfig.conf().toRst());
  }
}