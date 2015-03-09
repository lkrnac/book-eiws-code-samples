package net.lkrnac.book.eiws.chapter01.javascheduling;

public final class Application {
  public static void main(String... args) throws InterruptedException {
    new SimpleScheduler(new SimpleLogger()).scheduleTask();
  }
}
