package net.lkrnac.book.eiws.chapter08;

import net.lkrnac.book.eiws.chapter08.in.AsyncMessageSender;
import net.lkrnac.book.eiws.chapter08.out.TestWriteRepository;
import net.lkrnac.book.eiws.chapter08.out.WriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ActiveProfiles("integration-test")
@SpringApplicationConfiguration(classes = SiApplication.class)
public class SiApplicationTests extends AbstractTestNGSpringContextTests {
  private static final String MESSAGE_TEXT = "message3";

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
    TestWriteRepository testWriteRepository =
        (TestWriteRepository) writeRepository;
    Assert.assertEquals(testWriteRepository.getMessage(), MESSAGE_TEXT + ","
        + MESSAGE_TEXT);
  }
}
