package net.lkrnac.book.eiws.chapter09.read;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

@Repository
public class ReadRepository {
  private static final Iterator<String> ITERATOR = generateRecords(15)
      .iterator();

  public String readNext() {
    return (ITERATOR.hasNext()) ? ITERATOR.next() : null;
  }

  private static List<String> generateRecords(int count) {
    return Stream.iterate(0, n -> n + 1)
        .map(i -> "simple record " + i)
        .limit(count)
        .collect(Collectors.toList());
  }
}
