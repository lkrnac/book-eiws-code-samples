package net.lkrnac.book.eiws.chapter08;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

@WebIntegrationTest("server.port:0")
@SpringApplicationConfiguration(classes = SiApplication.class)
public class SiApplicationTests extends AbstractTestNGSpringContextTests {
  private static final String LOCALHOST = "http://localhost:";
  private static final String SELECT_COUNT =
      "select count(*) from TEXT_TABLE where text = ?";

  private static final String MESSAGE_TEXT = "simple message";

  @Value("${local.server.port}")
  private int serverPort;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void testSi() throws InterruptedException, IOException {
    // GIVEN
    RestTemplate restTemplate = new RestTemplate();

    // WHEN
    restTemplate.postForLocation(LOCALHOST + serverPort, "simple message");
    restTemplate.postForLocation(LOCALHOST + serverPort, "simple message");

    // THEN
    long count =
        jdbcTemplate.queryForObject(SELECT_COUNT, Long.class, MESSAGE_TEXT);
    Assert.assertEquals(count, 2);
  }
}
