package net.lkrnac.book.eiws.chapter01.scheduling.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
  private static final int SCHEDULING_DELAY = 1000;

  private final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS",
      Locale.getDefault());
  private final SimpleLogger logger;

  @Autowired
  public ScheduledTask(SimpleLogger logger) {
    this.logger = logger;
  }

  @Scheduled(fixedRate = SCHEDULING_DELAY)
  public void call() {
    logger.log(dateFormat.format(new Date()) + " - job kicked off");
  }
}
