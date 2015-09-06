package net.lkrnac.book.eiws.chapter09.write;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WriteRepository {
  public void writeRecords(List<String> records) {
    log.info("Starting to write records...");
    records.forEach(log::info);
  }
}
