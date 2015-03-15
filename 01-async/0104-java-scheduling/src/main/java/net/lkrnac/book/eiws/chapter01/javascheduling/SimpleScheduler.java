package net.lkrnac.book.eiws.chapter01.javascheduling;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimpleScheduler {
  private SimpleLogger logger;

  public SimpleScheduler(SimpleLogger logger) {
    super();
    this.logger = logger;
  }

  public void scheduleTask() {
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    ScheduledTask task = new ScheduledTask(logger);
    executor.scheduleAtFixedRate(task, 0L, 1, TimeUnit.SECONDS);
  }
}
