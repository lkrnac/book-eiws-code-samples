package net.lkrnac.book.eiws.chapter09.read;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Repository;

@Repository
public class ReadRepository {
  private static final Iterator<String> ITERATOR = generateRecords(15)
      .iterator();

  public synchronized String readNext() {
    return (ITERATOR.hasNext()) ? ITERATOR.next() : null;
  }

  private static List<String> generateRecords(int count) {
    return IntStream.range(0, count)
        .mapToObj(i -> "simple record " + i)
        .collect(Collectors.toList());
  }
}
