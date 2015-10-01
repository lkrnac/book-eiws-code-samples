package net.lkrnac.book.eiws.chapter09;

import java.util.List;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemWriterListener implements ItemWriteListener<String> {
  private SimpleExecutablePoint executablePoint;

  @Autowired
  public SimpleItemWriterListener(SimpleExecutablePoint executablePoint) {
    super();
    this.executablePoint = executablePoint;
  }

  @Override
  public void beforeWrite(List<? extends String> items) {
    executablePoint.execute("Starting to write items...");
  }

  @Override
  public void afterWrite(List<? extends String> items) {
    executablePoint.execute("Items written successfully");
  }

  @Override
  public void onWriteError(Exception exception, List<? extends String> items) {
    executablePoint.execute("Error occured while writing items");
  }
}
