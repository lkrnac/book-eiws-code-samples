package net.lkrnac.book.eiws.chapter09;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HotWaterStepListener {
  private SimpleExecutablePoint executablePoint;

  @Autowired
  public HotWaterStepListener(SimpleExecutablePoint executablePoint) {
    super();
    this.executablePoint = executablePoint;
  }

  @BeforeStep
  public void beforeHotWaterStep(StepExecution stepExecution) {
    executablePoint.execute("Be carefull with hot water!");
  }

  @AfterStep
  public ExitStatus afterHotWaterStep(StepExecution stepExecution) {
    executablePoint.execute("Step involving hot water manipulation is done");
    return ExitStatus.COMPLETED;
  }
}
