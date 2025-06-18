package org.otus.config;

import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.ws.client.WebServiceClient;
import com.consol.citrus.ws.server.WebServiceServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CitrusMockConfig {

    @Bean
    public WebServiceServer soapMockServer() {
        return CitrusEndpoints.soap()
            .server()
            .port(8080)
            .autoStart(true)
            .build();
    }


    @Bean
    public WebServiceClient soapClient() {
        return CitrusEndpoints.soap()
                .client()
                .defaultUri("http://localhost:8080")
                .build();
    }
}
