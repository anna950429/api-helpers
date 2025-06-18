package org.otus.tests.helpers;

import com.consol.citrus.message.MessageType;
import com.consol.citrus.dsl.runner.TestRunner;
import com.consol.citrus.dsl.runner.DefaultTestRunner;

public class SoapHelper {

  public static void sendSoapRequest(com.consol.citrus.dsl.runner.TestRunner runner,
      String soapEndpoint,
      String payload) {
    runner.soap(action -> action
        .client("soapClient")
        .send()
        .soapAction("SomeAction")
        .payload(payload)
    );
  }

  public static void receiveSoapResponse(com.consol.citrus.dsl.runner.TestRunner runner,
      String soapEndpoint) {
    runner.soap(action -> action
        .client("soapClient")
        .receive()
        .messageType(MessageType.XML)
        .payload("<ns:SomeResponse xmlns:ns=\"http://example.org\">OK</ns:SomeResponse>")
    );
  }
}
