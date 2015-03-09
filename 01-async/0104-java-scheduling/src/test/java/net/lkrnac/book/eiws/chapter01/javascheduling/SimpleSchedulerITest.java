package net.lkrnac.book.eiws.chapter01.javascheduling;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.testng.annotations.Test;

public class SimpleSchedulerITest {
  private static final int WAIT_FOR_SCHEDULING = 1000;

  @Test
  public void testScheduling() throws InterruptedException {
    // GIVEN
    SimpleLogger simpleLogger = mock(SimpleLogger.class);
    SimpleScheduler simpleScheduler = new SimpleScheduler(simpleLogger);

    // WHEN
    simpleScheduler.scheduleTask();
    Thread.sleep(WAIT_FOR_SCHEDULING);

    // THEN
    verify(simpleLogger, atLeast(2)).log(anyString());
  }
}
