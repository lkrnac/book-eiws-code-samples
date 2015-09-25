package net.lkrnac.book.eiws.chapter09.read;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleRecordReader implements ItemReader<String> {
  private ReadRepository readRepository;

  @Autowired
  public SimpleRecordReader(ReadRepository readRepository) {
    super();
    this.readRepository = readRepository;
  }

  @Override
  public String read() {
    return readRepository.readNext();
  }
}
