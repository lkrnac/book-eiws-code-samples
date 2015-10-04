package net.lkrnac.book.eiws.chapter09.write;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleRecordWriter implements ItemWriter<String> {
  private WriteRepository writeRepository;
  private int errorSimulated = 0;

  @Autowired
  public SimpleRecordWriter(WriteRepository writeRepository) {
    super();
    this.writeRepository = writeRepository;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void write(List<? extends String> items) throws Exception {
    preWriteHook();
    writeRepository.writeRecords((List<String>) items);
  }

  public void preWriteHook() {
    if (errorSimulated < 2) {
      errorSimulated++;
      throw new IllegalStateException("error occured");
    }
  }
}
