package net.lkrnac.book.eiws.chapter08;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

@WebIntegrationTest("server.port:0")
@SpringApplicationConfiguration(classes = SiApplication.class)
public class SiApplicationTests extends AbstractTestNGSpringContextTests {
  private static final String LOCALHOST = "http://localhost:";

  private static final String MESSAGE_TEXT = "simple message";

  @Value("${local.server.port}")
  private int serverPort;

  @Test
  public void testSi() throws InterruptedException, IOException {
    // GIVEN
    RestTemplate restTemplate = new RestTemplate();
    File file = new File("output-files/output.txt");
    file.delete();

    // WHEN
    restTemplate.postForLocation(LOCALHOST + serverPort, "simple message 1");
    restTemplate.postForLocation(LOCALHOST + serverPort, "simple message 2");

    // THEN
    String[] actualMessages =
        StringUtils.split(FileUtils.readFileToString(file), "\n");
    Assert.assertEquals(actualMessages[0], MESSAGE_TEXT + " 1");
    Assert.assertEquals(actualMessages[1], MESSAGE_TEXT + " 2");
  }
}
