package net.lkrnac.book.eiws.chapter08;

import net.lkrnac.book.eiws.chapter08.in.SiWrapperServiceTransacted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = SiApplication.class)
public class SiApplicationTests extends AbstractTestNGSpringContextTests {
  private static final String SELECT_COUNT = "select count(*) from TEXT_TABLE";

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private SiWrapperServiceTransacted wrapperService;

  @Test
  public void testSi() throws InterruptedException {
    // GIVEN

    // WHEN
    try {
      boolean result = wrapperService.processText("simple message");
    } catch (IllegalStateException ise) {
      // ignore error
    }

    Thread.sleep(1000);

    // THEN
    int count = jdbcTemplate.queryForObject(SELECT_COUNT, Integer.class);
    Assert.assertEquals(count, 0);
  }
}
