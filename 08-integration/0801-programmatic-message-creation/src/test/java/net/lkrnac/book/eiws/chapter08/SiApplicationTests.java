package net.lkrnac.book.eiws.chapter08;

import net.lkrnac.book.eiws.chapter08.in.SiWrapperServiceWithHeaders;
import net.lkrnac.book.eiws.chapter08.out.WriteRepositoryWithHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = SiApplication.class)
public class SiApplicationTests extends AbstractTestNGSpringContextTests {
  {
    System.setProperty("spring.profiles.active", "integration-test");
  }

  @Autowired
  private WriteRepositoryWithHeaders writeRepository;

  @Autowired
  private SiWrapperServiceWithHeaders wrapperService;

  @Test
  public void testSi() {
    // GIVEN
    Message<String> message =
        MessageBuilder.withPayload("simple message")
            .setHeader("simpleHeader", "simple header").build();

    // WHEN
    wrapperService.processMessage(message);

    // THEN
    TestWriteRepositoryWithHeaders testWriteRepository =
        (TestWriteRepositoryWithHeaders) writeRepository;
    Assert.assertEquals(testWriteRepository.getMessage(), new String[] {
        "simple message", "simple header" });
  }
}
