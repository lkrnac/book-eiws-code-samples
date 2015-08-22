package net.lkrnac.book.eiws.chapter08;

import net.lkrnac.book.eiws.chapter08.out.TestWriteRepository;
import net.lkrnac.book.eiws.chapter08.out.WriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "classpath:si-config.xml")
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
    TestWriteRepository testWriteRepository =
        (TestWriteRepository) writeRepository;
    Assert.assertEquals(testWriteRepository.getMessage(), MESSAGE_TEXT);
    Assert.assertEquals(testWriteRepository.getMessage(), MESSAGE_TEXT);
  }
}
