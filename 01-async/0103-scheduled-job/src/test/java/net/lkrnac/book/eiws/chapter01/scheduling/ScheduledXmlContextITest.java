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
  private static final int WAIT_FOR_SCHEDULING = 1000;

  @Autowired
  private SimpleLogger loggerSpy;

  @Test
  public void testScheduling() throws InterruptedException {
    Thread.sleep(WAIT_FOR_SCHEDULING);

    verify(loggerSpy, atLeast(2)).log(anyString());
  }
}
