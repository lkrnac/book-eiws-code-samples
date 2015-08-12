package net.lkrnac.book.eiws.chapter08;

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
  private SimpleService simpleService;

  @Test
  public void testSi() {
    // GIVEN

    // WHEN
    simpleService.processText("message1");
    simpleService.processText(2);

    // THEN
    TestWriteRepository testWriteService =
        (TestWriteRepository) writeRepository;
    Assert.assertEquals(testWriteService.getMessage(), "message1");
    Assert.assertEquals(testWriteService.getMessage(), "message2");
  }
}
