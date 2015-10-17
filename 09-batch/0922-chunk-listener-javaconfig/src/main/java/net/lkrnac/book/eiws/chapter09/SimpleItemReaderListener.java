package net.lkrnac.book.eiws.chapter09;

import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;

@Component
public class SimpleItemReaderListener {
  private SimpleExecutablePoint executablePoint;

  @Autowired
  public SimpleItemReaderListener(SimpleExecutablePoint executablePoint) {
    super();
    this.executablePoint = executablePoint;
  }

  @BeforeRead
  public void beforeRead() {
    executablePoint.execute("Starting to read...");
  }

  @AfterRead
  public void afterRead(String item) {
    executablePoint.execute("Read finished, item: " + item);
  }

  @OnReadError
  public void onReadError(Exception ex) {
    executablePoint.execute("Error occured while reading");
  }
}
