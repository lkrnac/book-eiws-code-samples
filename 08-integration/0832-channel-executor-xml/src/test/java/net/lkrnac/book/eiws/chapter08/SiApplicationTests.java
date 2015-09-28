package net.lkrnac.book.eiws.chapter08;

import net.lkrnac.book.eiws.chapter08.in.SiWrapperServiceVoid;
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
  @Autowired
  private WriteRepository writeRepository;

  @Autowired
  private SiWrapperServiceVoid wrapperService;

  @Test
  public void testSi() {
    // GIVEN

    // WHEN
    wrapperService.processText("message1");
    wrapperService.processText("message2");

    // THEN
    TestWriteRepository testWriteRepository =
        (TestWriteRepository) writeRepository;
    String message1 = testWriteRepository.getMessage();
    if ("message1".equals(message1)) {
      Assert.assertEquals(testWriteRepository.getMessage(), "message2");
    } else {
      Assert.assertEquals(testWriteRepository.getMessage(), "message1");
      Assert.assertEquals(message1, "message2");
    }
  }
}
