package net.lkrnac.book.eiws.chapter01.scheduling;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import net.lkrnac.book.eiws.chapter01.scheduling.task.SimpleLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(locations = "classpath:scheduled-context.xml")
public class ScheduledXmlContextITest extends AbstractTestNGSpringContextTests {
  @Autowired
  private SimpleLogger loggerSpy;

  @Test
  public void verifyScheduling() throws InterruptedException {
    Thread.sleep(1000);

    verify(loggerSpy, atLeast(2)).log(anyString());
  }
}
