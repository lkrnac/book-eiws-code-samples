package net.lkrnac.book.eiws.chapter09.write;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class WriteRepository {
  public void writeRecords(List<String> records) {
    System.out.println("Starting to write records...");
    records.forEach(System.out::println);
  }
}
