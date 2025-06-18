// src/test/java/org/otus/tests/StubFrontendTest.java
package org.otus.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StubFrontendTest {

  @Test
  void loadUsersShowsEmail() throws Exception {
    // === Selenoid desired capabilities ===
    ChromeOptions options = new ChromeOptions();
    Map<String, Object> selenoid = new HashMap<>();
    selenoid.put("enableVNC", true);
    options.setCapability("selenoid:options", selenoid);

    WebDriver driver = new RemoteWebDriver(
        new URL(System.getProperty("selenoid.url", "http://localhost:4444/wd/hub")),
        options
    );

    // index.html սպասարկվում է Jetty‑ով կամ ուղղակի ֆայլային ուղի
    String indexPath = Paths.get("src", "main", "resources", "public", "index.html")
        .toAbsolutePath().toUri().toString();
    driver.get(indexPath);

    driver.findElement(By.xpath("//button[contains(.,'Load Users')]")).click();
    Thread.sleep(1000); // demo wait

    String preText = driver.findElement(By.id("output")).getText();
    assertTrue(preText.contains("test@test.test"), "Email not found in UI");

    driver.quit();
  }
}
