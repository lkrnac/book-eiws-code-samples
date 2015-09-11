package net.lkrnac.book.eiws.chapter09;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeaJobListener {
  private SimpleExecutablePoint executablePoint;

  @Autowired
  public TeaJobListener(SimpleExecutablePoint executablePoint) {
    super();
    this.executablePoint = executablePoint;
  }

  @BeforeJob
  public void beforeJob(JobExecution jobExecution) {
    executablePoint.execute("It's tea time!");
  }

  @AfterJob
  public void afterJob(JobExecution jobExecution) {
    executablePoint.execute("Enjoy your tea!");
  }
}
