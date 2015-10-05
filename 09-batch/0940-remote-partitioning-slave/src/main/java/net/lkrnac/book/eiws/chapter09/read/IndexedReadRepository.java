package net.lkrnac.book.eiws.chapter09.read;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Repository;

@Repository
public class IndexedReadRepository {
  private static final List<String> RECORDS_LIST = generateRecords(16);

  public synchronized String getRecord(int index) {
    return RECORDS_LIST.get(index);
  }

  private static List<String> generateRecords(int count) {
    return IntStream.range(0, count)
        .mapToObj(idx -> "simple record " + idx)
        .collect(Collectors.toList());
  }
}
