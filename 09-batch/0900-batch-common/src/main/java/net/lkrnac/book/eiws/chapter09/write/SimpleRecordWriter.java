package net.lkrnac.book.eiws.chapter09.write;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleRecordWriter implements ItemWriter<String> {
  private WriteRepository writeRepository;

  @Autowired
  public SimpleRecordWriter(WriteRepository writeRepository) {
    super();
    this.writeRepository = writeRepository;
  }

  @Override
  public void write(List<? extends String> items) throws Exception {
    writeRepository.writeRecords(items);
  }
}
