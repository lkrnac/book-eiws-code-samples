package net.lkrnac.book.eiws.chapter09.write;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleRecordWriterDiscard implements ItemWriter<String> {
  private static final String CHUNK_COUNT = "chunkCount";
  private WriteRepository writeRepository;
  private ExecutionContext executionContext;

  @Autowired
  public SimpleRecordWriterDiscard(WriteRepository writeRepository) {
    super();
    this.writeRepository = writeRepository;
  }

  @BeforeStep
  public void storeExectionContext(StepExecution stepExecution) {
    this.executionContext = stepExecution.getExecutionContext();
    executionContext.put(CHUNK_COUNT, 0);
  }

  @Override
  public void write(List<? extends String> items) throws Exception {
    int chunkCount = executionContext.getInt(CHUNK_COUNT);
    List<String> filteredItems = new ArrayList<>();
    if (chunkCount % 2 == 0) {
      filteredItems.add(items.get(0));
    } else {
      filteredItems.addAll(items);
    }
    executionContext.put(CHUNK_COUNT, chunkCount + 1);

    writeRepository.writeRecords(filteredItems);
  }
}
