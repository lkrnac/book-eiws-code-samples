package net.lkrnac.book.eiws.chapter09;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeaJobListener implements JobExecutionListener {
  private SimpleExecutablePoint executablePoint;

  @Autowired
  public TeaJobListener(SimpleExecutablePoint executablePoint) {
    super();
    this.executablePoint = executablePoint;
  }

  @Override
  public void beforeJob(JobExecution jobExecution) {
    executablePoint.execute("It's tea time!");
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    executablePoint.execute("Enjoy your tea!");
  }
}
