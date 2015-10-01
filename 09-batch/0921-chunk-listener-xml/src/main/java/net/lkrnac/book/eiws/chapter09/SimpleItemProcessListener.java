package net.lkrnac.book.eiws.chapter09;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;

import org.springframework.batch.core.annotation.AfterProcess;
import org.springframework.batch.core.annotation.BeforeProcess;
import org.springframework.batch.core.annotation.OnProcessError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemProcessListener {
  private SimpleExecutablePoint executablePoint;

  @Autowired
  public SimpleItemProcessListener(SimpleExecutablePoint executablePoint) {
    super();
    this.executablePoint = executablePoint;
  }

  @BeforeProcess
  public void beforeProcess(String item) {
    executablePoint.execute("Starting to process item: " + item);
  }

  @AfterProcess
  public void afterProcess(String item, String result) {
    executablePoint.execute("Processed item: " + result);
  }

  @OnProcessError
  public void onProcessError(String item, Exception e) {
    executablePoint.execute("Error occured while processing item: " + item);
  }
}
