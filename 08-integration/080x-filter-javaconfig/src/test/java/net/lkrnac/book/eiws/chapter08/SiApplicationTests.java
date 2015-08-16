package net.lkrnac.book.eiws.chapter08;

import java.io.IOException;

import net.lkrnac.book.eiws.chapter08.out.TestWriteRepository;
import net.lkrnac.book.eiws.chapter08.out.WriteRepository;

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
    restTemplate.postForLocation(url, MESSAGE_TEXT);
    restTemplate.postForLocation(url, "corruptedMessage");
    restTemplate.postForLocation(url, MESSAGE_TEXT);

    // THEN
    TestWriteRepository testWriteRepository =
        (TestWriteRepository) writeRepository;
    Assert.assertEquals(testWriteRepository.getMessage(), MESSAGE_TEXT);
    Assert.assertEquals(testWriteRepository.getMessage(), MESSAGE_TEXT);
  }
}
