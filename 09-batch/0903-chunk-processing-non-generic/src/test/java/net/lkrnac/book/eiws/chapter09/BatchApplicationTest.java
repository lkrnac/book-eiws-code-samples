package net.lkrnac.book.eiws.chapter09;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = BatchApplication.class)
public class BatchApplicationTest extends AbstractTestNGSpringContextTests {
  private static final String SELECT_COUNT =
      "select count(*) from USERS where EMAIL like ?";

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test(timeOut = 5000)
  public void testBatch() throws InterruptedException {
    // GIVEN - Spring configuration

    // WHEN - Spring Batch job is started automatically
    Thread.sleep(1000);

    // THEN
    long count =
        jdbcTemplate.queryForObject(SELECT_COUNT, Long.class,
            "%@%");
    Assert.assertEquals(count, 6);
  }
}
