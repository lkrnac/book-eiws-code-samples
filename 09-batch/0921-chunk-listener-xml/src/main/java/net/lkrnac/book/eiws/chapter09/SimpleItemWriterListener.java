package net.lkrnac.book.eiws.chapter09;

import java.util.List;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;

import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.annotation.OnWriteError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemWriterListener {
  private SimpleExecutablePoint executablePoint;

  @Autowired
  public SimpleItemWriterListener(SimpleExecutablePoint executablePoint) {
    super();
    this.executablePoint = executablePoint;
  }

  @BeforeWrite
  public void beforeWrite(List<? extends String> items) {
    executablePoint.execute("Starting to write items...");
  }

  @AfterWrite
  public void afterWrite(List<? extends String> items) {
    executablePoint.execute("Items written successfully");
  }

  @OnWriteError
  public void onWriteError(Exception exception, List<? extends String> items) {
    executablePoint.execute("Error occured while writing items");
  }
}
