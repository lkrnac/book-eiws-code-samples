package net.lkrnac.book.eiws.chapter09;

import org.springframework.batch.core.ItemProcessListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;

@Component
public class SimpleItemProcessListener implements
    ItemProcessListener<String, String> {
  private SimpleExecutablePoint executablePoint;

  @Autowired
  public SimpleItemProcessListener(SimpleExecutablePoint executablePoint) {
    super();
    this.executablePoint = executablePoint;
  }

  @Override
  public void beforeProcess(String item) {
    executablePoint.execute("Starting to process item: " + item);
  }

  @Override
  public void afterProcess(String item, String result) {
    executablePoint.execute("Processed item: " + result);
  }

  @Override
  public void onProcessError(String item, Exception e) {
    executablePoint.execute("Error occured while processing item: " + item);
  }
}
