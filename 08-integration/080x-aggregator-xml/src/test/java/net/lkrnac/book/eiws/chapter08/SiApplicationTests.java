package net.lkrnac.book.eiws.chapter08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = SiApplication.class)
public class SiApplicationTests extends AbstractTestNGSpringContextTests {
  private static final String MESSAGE_TEXT = "message3";

  {
    System.setProperty("spring.profiles.active", "integration-test");
  }

  @Autowired
  private WriteRepository writeRepository;

  @Autowired
  private AsyncMessageSender asyncMessageSender;

  @Test
  public void testSi() throws Exception {
    // GIVEN

    // WHEN
    asyncMessageSender.sendMessage(MESSAGE_TEXT);
    asyncMessageSender.sendMessage(MESSAGE_TEXT);

    // THEN
    TestWriteRepository testWriteService =
        (TestWriteRepository) writeRepository;
    Assert.assertEquals(testWriteService.getMessage(), MESSAGE_TEXT + ","
        + MESSAGE_TEXT);
  }
}
