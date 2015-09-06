package net.lkrnac.book.eiws.chapter09.read;

import net.lkrnac.book.eiws.chapter09.read.ReadRepository;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
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
  public String read() throws Exception, UnexpectedInputException,
      ParseException, NonTransientResourceException {
    return readRepository.readNext();
  }
}
