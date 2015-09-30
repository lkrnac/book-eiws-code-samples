package net.lkrnac.book.eiws.chapter09.write;

import java.util.List;

import net.lkrnac.book.eiws.chapter09.WrittenRecordsCounter;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatefulRecordWriter implements ItemWriter<String> {
  private WriteRepository writeRepository;
  private WrittenRecordsCounter recordsCounter;

  @Autowired
  public StatefulRecordWriter(WriteRepository writeRepository,
      WrittenRecordsCounter recordsCounter) {
    super();
    this.writeRepository = writeRepository;
    this.recordsCounter = recordsCounter;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void write(List<? extends String> items) throws Exception {
    writeRepository.writeRecords((List<String>) items);
    int previousRecordsCount = recordsCounter.getProcessedCount();
    recordsCounter.setProcessedCount(previousRecordsCount + items.size());
  }
}
