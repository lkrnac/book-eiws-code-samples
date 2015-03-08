package net.lkrnac.book.eiws.chapter01.javascheduling;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScheduledTask implements Runnable {
  private final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS",
      Locale.getDefault());

  private final SimpleLogger logger;

  public ScheduledTask(SimpleLogger logger) {
    this.logger = logger;
  }

  public void run() {
    logger.log(dateFormat.format(new Date()) + " - job kicked off");
  }
}
