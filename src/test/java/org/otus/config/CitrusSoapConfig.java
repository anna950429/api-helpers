package org.otus.config;

import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.ws.client.WebServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class CitrusSoapConfig {

  @Bean
  public WebServiceClient soapClient() {
    return CitrusEndpoints.soap()
        .client()
        .defaultUri("http://localhost:8080/ws")
        .build();
  }
}