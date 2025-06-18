package org.otus.tests;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.runner.TestRunner;
import com.consol.citrus.junit.jupiter.CitrusBaseExtension;
import com.consol.citrus.junit.jupiter.CitrusSupport;
import org.junit.jupiter.api.extension.ExtendWith;
import org.otus.config.CitrusMockConfig;
import org.springframework.test.context.ContextConfiguration;

@ExtendWith(CitrusBaseExtension.class)
@CitrusSupport
@ContextConfiguration(classes = CitrusMockConfig.class)
public class MyCitrusJunit5Test {

  @CitrusTest
  void soapTest(TestRunner runner) {

    runner.soap(action -> action
        .client("soapClient")
        .send()
        .soapAction("SomeAction")
        .payload("<ns:SomeRequest xmlns:ns=\"http://example.org\">ping</ns:SomeRequest>")
    );

    runner.soap(action -> action
        .server("soapMockServer")
        .receive()
        .payload("<ns:SomeRequest xmlns:ns=\"http://example.org\">ping</ns:SomeRequest>")
    );

    runner.soap(action -> action
        .server("soapMockServer")
        .send()
        .payload("<ns:SomeResponse xmlns:ns=\"http://example.org\">OK</ns:SomeResponse>")
    );

    runner.soap(action -> action
        .client("soapClient")
        .receive()
        .payload("<ns:SomeResponse xmlns:ns=\"http://example.org\">OK</ns:SomeResponse>")
    );
  }



}
