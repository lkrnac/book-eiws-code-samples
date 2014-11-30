package net.lkrnac.book.eiws.chapter01.scheduling.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
  private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");

  private SimpleLogger logger;

  @Autowired
  public ScheduledTask(SimpleLogger logger) {
    this.logger = logger;
  }

  @Scheduled(fixedRate = 1000)
  public void call() {
    logger.log(dateFormat.format(new Date()) + " - job kicked off");
  }
}
