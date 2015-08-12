package net.lkrnac.book.eiws.chapter08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = SiApplication.class)
public class SiApplicationTests extends AbstractTestNGSpringContextTests {
  private static final String MESSAGE_TEXT = "simple message";

  {
    System.setProperty("spring.profiles.active", "integration-test");
  }

  @Autowired
  private WriteRepository writeRepository;

  @Test(timeOut = 3000)
  public void testSi() throws InterruptedException {
    // GIVEN: Spring configuration

    // WHEN

    // THEN
    TestWriteRepository testWriteService =
        (TestWriteRepository) writeRepository;
    Assert.assertEquals(testWriteService.getMessage(), MESSAGE_TEXT);
    Assert.assertEquals(testWriteService.getMessage(), MESSAGE_TEXT);
  }
}
