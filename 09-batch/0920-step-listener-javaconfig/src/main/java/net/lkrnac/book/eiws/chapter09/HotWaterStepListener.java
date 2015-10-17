package net.lkrnac.book.eiws.chapter09;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;

@Component
public class HotWaterStepListener {
  private SimpleExecutablePoint executablePoint;

  @Autowired
  public HotWaterStepListener(SimpleExecutablePoint executablePoint) {
    super();
    this.executablePoint = executablePoint;
  }

  @BeforeStep
  public void beforeHotWaterStep() {
    executablePoint.execute("Be careful with hot water!");
  }

  @AfterStep
  public ExitStatus afterHotWaterStep() {
    executablePoint.execute("Step involving hot water manipulation is done");
    return ExitStatus.COMPLETED;
  }
}
