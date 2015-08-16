package net.lkrnac.book.eiws.chapter08;

import java.io.IOException;

import net.lkrnac.book.eiws.chapter08.in.SiWrapperService;
import net.lkrnac.book.eiws.chapter08.out.TestWriteRepository;
import net.lkrnac.book.eiws.chapter08.out.WriteRepository;

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

  @Autowired
  private SiWrapperService wrapperService;

  @Test
  public void testSi() throws InterruptedException, IOException {
    // GIVEN

    // WHEN
    boolean result1 = wrapperService.processText(MESSAGE_TEXT);
    boolean result2 = wrapperService.processText(MESSAGE_TEXT);

    // THEN
    TestWriteRepository testWriteRepository =
        (TestWriteRepository) writeRepository;
    Assert.assertEquals(testWriteRepository.getMessage(), MESSAGE_TEXT);
    Assert.assertEquals(result1, true);
    Assert.assertEquals(testWriteRepository.getMessage(), MESSAGE_TEXT);
    Assert.assertEquals(result2, true);
  }
}
