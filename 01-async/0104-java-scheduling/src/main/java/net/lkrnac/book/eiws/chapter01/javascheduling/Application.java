package net.lkrnac.book.eiws.chapter01.javascheduling;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class Application {
  public static void main(String... args) throws InterruptedException {
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
    ScheduledTask task = new ScheduledTask(new SimpleLogger());
    executor.scheduleAtFixedRate(task, 0L, 1000, TimeUnit.MILLISECONDS);
  }
}
