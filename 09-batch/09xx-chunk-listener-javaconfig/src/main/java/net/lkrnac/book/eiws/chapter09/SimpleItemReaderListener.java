package net.lkrnac.book.eiws.chapter09;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemReaderListener implements ItemReadListener<String> {
  private SimpleExecutablePoint executablePoint;

  @Autowired
  public SimpleItemReaderListener(SimpleExecutablePoint executablePoint) {
    super();
    this.executablePoint = executablePoint;
  }

  @Override
  public void beforeRead() {
    executablePoint.execute("Starting to read...");
  }

  @Override
  public void afterRead(String item) {
    executablePoint.execute("Read finished, item: " + item);
  }

  @Override
  public void onReadError(Exception ex) {
    executablePoint.execute("Error occured while reading");
  }

}
