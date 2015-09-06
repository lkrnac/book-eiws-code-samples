package net.lkrnac.book.eiws.chapter09.process;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class SimpleRecordProcessor implements ItemProcessor<String, String> {
  @Override
  public String process(String item) throws Exception {
    return item + " processed";
  }
}
