package net.lkrnac.book.eiws.chapter08;

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
  {
    System.setProperty("spring.profiles.active", "integration-test");
  }

  @Autowired
  private WriteRepository writeRepository;

  @Autowired
  private SiWrapperService wrapperService;

  @Test
  public void testSi() {
    // GIVEN

    // WHEN
    wrapperService.processMessage("message1");
    wrapperService.processMessage(2);

    // THEN
    TestWriteRepository testWriteRepository =
        (TestWriteRepository) writeRepository;
    Assert.assertEquals(testWriteRepository.getMessage(), "message1");
    Assert.assertEquals(testWriteRepository.getMessage(), "message2");
  }
}
