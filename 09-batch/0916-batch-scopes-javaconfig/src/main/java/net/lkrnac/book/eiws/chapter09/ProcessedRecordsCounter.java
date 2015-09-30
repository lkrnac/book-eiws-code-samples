package net.lkrnac.book.eiws.chapter09;

import lombok.Data;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.stereotype.Component;

@Data
@JobScope
@Component
public class ProcessedRecordsCounter {
  private int processedCount;
}
