package net.lkrnac.book.eiws.chapter09;

import java.util.List;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;
import net.lkrnac.book.eiws.chapter09.write.WriteRepository;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StreamRecordWriter implements ItemStreamWriter<String> {
  private WriteRepository writeRepository;
  private SimpleExecutablePoint executablePoint;

  @Autowired
  public StreamRecordWriter(WriteRepository writeRepository,
      SimpleExecutablePoint executablePoint) {
    super();
    this.writeRepository = writeRepository;
    this.executablePoint = executablePoint;
  }

  @Override
  public void write(List<? extends String> items) throws Exception {
    writeRepository.writeRecords(items);
  }

  @Override
  public void open(ExecutionContext executionContext)
      throws ItemStreamException {
    executablePoint.execute("Opening records writer");
  }

  @Override
  public void update(ExecutionContext executionContext)
      throws ItemStreamException {
    executablePoint.execute("Updating records writer");
  }

  @Override
  public void close() throws ItemStreamException {
    executablePoint.execute("Closing records writer");
  }
}
