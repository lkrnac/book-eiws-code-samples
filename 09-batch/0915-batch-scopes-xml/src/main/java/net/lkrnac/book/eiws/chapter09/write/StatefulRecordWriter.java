package net.lkrnac.book.eiws.chapter09.write;

import java.util.List;

import net.lkrnac.book.eiws.chapter09.ProcessedRecordsCounter;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatefulRecordWriter implements ItemWriter<String>,
    ItemWriteListener<String> {
  private WriteRepository writeRepository;
  private ProcessedRecordsCounter recordsCounter;

  @Autowired
  public StatefulRecordWriter(WriteRepository writeRepository,
      ProcessedRecordsCounter recordsCounter) {
    super();
    this.writeRepository = writeRepository;
    this.recordsCounter = recordsCounter;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void write(List<? extends String> items) throws Exception {
    writeRepository.writeRecords((List<String>) items);
  }

  @Override
  public void beforeWrite(List<? extends String> items) {
    // no action needed
  }

  @Override
  public void afterWrite(List<? extends String> items) {
    int previousRecordsCount = recordsCounter.getProcessedCount();
    recordsCounter.setProcessedCount(previousRecordsCount + items.size());
  }

  @Override
  public void onWriteError(Exception exception, List<? extends String> items) {
    // no action needed
  }
}
