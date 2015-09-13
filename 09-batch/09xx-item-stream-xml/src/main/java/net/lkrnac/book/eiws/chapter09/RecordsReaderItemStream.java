package net.lkrnac.book.eiws.chapter09;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecordsReaderItemStream implements ItemStream {
  private SimpleExecutablePoint executablePoint;

  @Autowired
  public RecordsReaderItemStream(SimpleExecutablePoint executablePoint) {
    super();
    this.executablePoint = executablePoint;
  }

  @Override
  public void open(ExecutionContext executionContext)
      throws ItemStreamException {
    executablePoint.execute("Opening records reader");
  }

  @Override
  public void update(ExecutionContext executionContext)
      throws ItemStreamException {
    executablePoint.execute("Updating records reader");
  }

  @Override
  public void close() throws ItemStreamException {
    executablePoint.execute("Closing records reader");
  }
}
