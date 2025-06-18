package org.otus.tests;

import org.junit.jupiter.api.*;
import org.otus.tests.helpers.MqHelper;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.*;

class MqHelperTest {

  static RabbitMQContainer rabbit;

  @BeforeAll
  static void startRabbit() {
    rabbit = new RabbitMQContainer(DockerImageName.parse("rabbitmq:3.13-alpine"));
    rabbit.start();
  }

  @AfterAll
  static void stopRabbit() {
    rabbit.stop();
  }

  @Test
  void roundTrip() throws Exception {
    MqHelper helper = new MqHelper(
        rabbit.getHost(),
        rabbit.getAmqpPort()
    );
    helper.sendMessage("demo", "hello");
    assertEquals("hello", helper.receiveMessage("demo"));
  }
}
