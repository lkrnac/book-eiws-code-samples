package net.lkrnac.book.eiws.chapter09;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;

import org.springframework.batch.core.SkipListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleSkipListener implements SkipListener<String, String> {
  private SimpleExecutablePoint executablePoint;

  @Autowired
  public SimpleSkipListener(SimpleExecutablePoint executablePoint) {
    super();
    this.executablePoint = executablePoint;
  }

  @Override
  public void onSkipInRead(Throwable t) {
    executablePoint.execute("Skipping read because of error");
  }

  @Override
  public void onSkipInWrite(String item, Throwable t) {
    executablePoint
        .execute("Skipping write of '" + item + "' because of error");
  }

  @Override
  public void onSkipInProcess(String item, Throwable t) {
    executablePoint
        .execute("Skipping processing of '" + item + "' because of error");
  }
}
