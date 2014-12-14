package net.lkrnac.book.eiws.chapter01.async;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import net.lkrnac.book.eiws.chapter01.async.task.Caller;
import net.lkrnac.book.eiws.chapter01.async.task.SimpleLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(locations = "classpath:async-context.xml")
public class AsyncXmlContextITest extends AbstractTestNGSpringContextTests {
  private static final int TEST_TIMEOUT = 2000;
  private static final int EXEC_COUNT = 10;

  @Autowired
  private SimpleLogger loggerSpy;

  @Autowired
  private Caller caller;

  @Test(timeOut = TEST_TIMEOUT)
  public void testContextLoads() throws InterruptedException {
    // GIVEN - Spring configuration

    // WHEN
    caller.kickOffAsyncTasks(EXEC_COUNT);

    // THEN
    verify(loggerSpy, times(EXEC_COUNT + 1)).log(anyString());
  }
}
