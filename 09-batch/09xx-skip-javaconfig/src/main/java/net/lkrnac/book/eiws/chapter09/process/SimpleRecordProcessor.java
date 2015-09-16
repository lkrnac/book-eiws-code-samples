package net.lkrnac.book.eiws.chapter09.process;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class SimpleRecordProcessor implements ItemProcessor<String, String> {
  private boolean errorSimulated = false;

  @Override
  public String process(String item) throws Exception {
    preProcessHook();
    return item + " processed";
  }

  public void preProcessHook() {
    if (!errorSimulated) {
      errorSimulated = true;
      throw new IllegalStateException("error occured");
    }
  }
}
