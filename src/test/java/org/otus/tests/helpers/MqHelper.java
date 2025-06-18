package org.otus.tests.helpers;

import com.rabbitmq.client.*;

public class MqHelper {

  private final ConnectionFactory factory;

  public MqHelper(String host, int port) {
    factory = new ConnectionFactory();
    factory.setHost(host);
    factory.setPort(port);
  }

  public void sendMessage(String queue, String message) throws Exception {
    try (Connection conn = factory.newConnection();
        Channel ch = conn.createChannel()) {
      ch.queueDeclare(queue, false, false, false, null);
      ch.basicPublish("", queue, null, message.getBytes());
    }
  }

  public String receiveMessage(String queue) throws Exception {
    try (Connection conn = factory.newConnection();
        Channel ch = conn.createChannel()) {
      GetResponse resp = ch.basicGet(queue, true);
      return resp == null ? null : new String(resp.getBody());
    }
  }
}
