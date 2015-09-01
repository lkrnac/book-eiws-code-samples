package net.lkrnac.book.eiws.chapter08;

import net.lkrnac.book.eiws.chapter08.in.SiWrapperServiceVoid;

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
  private SiWrapperServiceVoid wrapperService;

  @Test
  public void testSi() throws InterruptedException {
    // GIVEN

    // WHEN
    wrapperService.processText("simple message");

    // THEN
    Thread.sleep(500);
    int count = jdbcTemplate.queryForObject(SELECT_COUNT, Integer.class);
    Assert.assertEquals(count, 0);
  }
}
