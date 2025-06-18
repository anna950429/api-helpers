package org.otus.stub;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class StubServer {

  private final WireMockServer wireMockServer;

  public StubServer() {
    this(
        Integer.parseInt(System.getProperty("stub.port", "8089")),
        System.getProperty("stub.resources", "src/main/resources/wiremock")
    );
  }

  public StubServer(int port, String resourcesDir) {
    wireMockServer = new WireMockServer(
        WireMockConfiguration.wireMockConfig()
            .port(port)
            .usingFilesUnderDirectory(resourcesDir)
    );
  }

  public void startServer() {
    wireMockServer.start();
    System.out.printf("WireMock started on :%d%n", wireMockServer.port());
  }

  public void stopServer() {
    wireMockServer.stop();
  }

  public boolean isRunning() {
    return wireMockServer.isRunning();
  }
}
