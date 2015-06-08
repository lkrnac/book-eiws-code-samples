package net.lkrnac.book.eiws.chapter06.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = JmsApplication.class)
public class JmsApplicationTests extends AbstractTestNGSpringContextTests {
  private static final String SELECT_COUNT =
      "select count(*) from TEXT_TABLE where text = ?";

  private static final String MESSAGE_TEXT = "simple message";

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private SimpleService simpleService;

  @Test(timeOut = 3000)
  public void testJms() throws Exception {
    // GIVEN: Spring configuration

    // WHEN
    Thread.sleep(2000);

    // THEN
    long count =
        jdbcTemplate.queryForObject(SELECT_COUNT, Long.class, MESSAGE_TEXT);
    Assert.assertEquals(count, 2);
  }
}
