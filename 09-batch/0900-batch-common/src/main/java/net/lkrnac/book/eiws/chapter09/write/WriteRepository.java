package net.lkrnac.book.eiws.chapter09.write;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class WriteRepository {
  public void writeRecords(List<? extends String> records) {
    records.stream()
        .map(record -> "Writing record: " + record)
        .forEach(log::info);
  }
}
