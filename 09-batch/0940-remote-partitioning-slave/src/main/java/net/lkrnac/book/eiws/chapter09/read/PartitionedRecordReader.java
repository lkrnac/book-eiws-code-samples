package net.lkrnac.book.eiws.chapter09.read;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartitionedRecordReader implements ItemReader<String> {
  private IndexedReadRepository readRepository;
  private ExecutionContext executionContext;
  private int partitionEnd;

  @Autowired
  public PartitionedRecordReader(IndexedReadRepository readRepository) {
    super();
    this.readRepository = readRepository;
  }

  @BeforeStep
  public void storeExecutionContext(StepExecution stepExecution) {
    this.executionContext = stepExecution.getExecutionContext();
    this.partitionEnd = executionContext.getInt("partitionEnd");
  }

  @Override
  public String read() {
    int currentIndex = executionContext.getInt("currentIndex");
    String item = (currentIndex <= partitionEnd)
        ? readRepository.getRecord(currentIndex)
        : null;
    executionContext.putInt("currentIndex", currentIndex + 1);
    return item;
  }
}
