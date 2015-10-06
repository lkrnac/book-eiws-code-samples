package net.lkrnac.book.eiws.chapter09;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RecordsPartitioner implements Partitioner {
  @Override
  public Map<String, ExecutionContext> partition(int gridSize) {
    int min = 0;
    int max = 16;
    int targetSize = (max - min) / gridSize;
    log.info("***** Parition distribution *****");
    log.info("min = " + min + " max = " + max + " targetSize = " + targetSize);

    Map<String, ExecutionContext> result = new HashMap<String, ExecutionContext>();
    int partitionIdx = 0;
    int start = min;
    int end = start + targetSize - 1;

    while (start < max) {
      ExecutionContext value = new ExecutionContext();
      result.put("partition" + partitionIdx, value);

      if (end > max) {
        end = max - 1;
      }
      log.info("partitionStart" + partitionIdx + " = " + start);
      log.info("partitionEnd" + partitionIdx + " = " + end);

      value.putInt("currentIndex", start);
      value.putInt("partitionEnd", end);
      start += targetSize;
      end += targetSize;
      partitionIdx++;
    }

    log.info("partitions = " + result.size());
    log.info("*********************************");
    return result;
  }
}
