package net.lkrnac.book.eiws.chapter08;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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

  {
    System.setProperty("spring.profiles.active", "integration-test");
  }

  @Value("${local.server.port}")
  private int serverPort;

  @Autowired
  private WriteRepository writeRepository;

  @Test
  public void testSi() throws InterruptedException, IOException {
    // GIVEN
    RestTemplate restTemplate = new RestTemplate();
    String url = LOCALHOST + serverPort;

    // WHEN
    String response1 =
        restTemplate.postForObject(url, MESSAGE_TEXT, String.class);
    String response2 =
        restTemplate.postForObject(url, "corruptedMessage", String.class);
    String response3 =
        restTemplate.postForObject(url, MESSAGE_TEXT, String.class);

    // THEN
    TestWriteRepository testWriteService =
        (TestWriteRepository) writeRepository;
    Assert.assertEquals(testWriteService.getMessage(), MESSAGE_TEXT);
    Assert.assertEquals(testWriteService.getMessage(), MESSAGE_TEXT);
    Assert.assertEquals(response1, "true");
    Assert.assertEquals(response2, null);
    Assert.assertEquals(response3, "true");
    // THEN
  }
}
