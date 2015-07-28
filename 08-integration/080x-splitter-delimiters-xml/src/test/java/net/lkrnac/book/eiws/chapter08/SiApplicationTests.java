package net.lkrnac.book.eiws.chapter08;

import java.io.IOException;

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
  public void testSi() throws InterruptedException, IOException {
    // GIVEN

    // WHEN
    boolean resultSuccess =
        simpleService.processText("messageSuccess;messageFail;messageSuccess");
    boolean resultFail =
        simpleService.processText("messageSuccess;messageFail");

    // THEN
    TestWriteRepository testWriteService =
        (TestWriteRepository) writeRepository;
    Assert.assertEquals(testWriteService.getMessage(), "messageSuccess");
    Assert.assertEquals(testWriteService.getMessage(), "messageFail");
    Assert.assertEquals(testWriteService.getMessage(), "messageSuccess");
    Assert.assertEquals(resultSuccess, true);

    Assert.assertEquals(testWriteService.getMessage(), "messageSuccess");
    Assert.assertEquals(testWriteService.getMessage(), "messageFail");
    Assert.assertEquals(resultFail, false);
  }
}
