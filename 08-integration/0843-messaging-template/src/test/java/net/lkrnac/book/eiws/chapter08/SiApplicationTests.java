package net.lkrnac.book.eiws.chapter08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import net.lkrnac.book.eiws.chapter08.out.TestWriteRepository;
import net.lkrnac.book.eiws.chapter08.out.WriteRepository;

@ActiveProfiles("integration-test")
@SpringApplicationConfiguration(classes = SiApplication.class)
public class SiApplicationTests extends AbstractTestNGSpringContextTests {
  private static final String IN_CHANNEL = "inChannel";

  private static final String MESSAGE_TEXT = "simple message";

  @Autowired
  private WriteRepository writeRepository;

  @Autowired
  private MessagingTemplate messagingTemplate;

  @Test
  public void testSi() {
    // GIVEN

    // WHEN
    boolean result1 = messagingTemplate.convertSendAndReceive(
        IN_CHANNEL, MESSAGE_TEXT, Boolean.class);
    boolean result2 = messagingTemplate.convertSendAndReceive(
        IN_CHANNEL, MESSAGE_TEXT, Boolean.class);

    // THEN
    TestWriteRepository testWriteRepository =
        (TestWriteRepository) writeRepository;
    Assert.assertEquals(testWriteRepository.getMessage(), MESSAGE_TEXT);
    Assert.assertEquals(result1, true);
    Assert.assertEquals(testWriteRepository.getMessage(), MESSAGE_TEXT);
    Assert.assertEquals(result2, true);

  }
}
