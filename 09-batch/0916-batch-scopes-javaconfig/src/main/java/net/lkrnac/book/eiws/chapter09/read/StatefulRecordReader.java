package net.lkrnac.book.eiws.chapter09.read;

import net.lkrnac.book.eiws.chapter09.ReadCountRestricter;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatefulRecordReader implements ItemReader<String> {
  private ReadRepository readRepository;
  private ReadCountRestricter readCountRestricter;

  @Autowired
  public StatefulRecordReader(ReadRepository readRepository,
      ReadCountRestricter readCountRestricter) {
    super();
    this.readRepository = readRepository;
    this.readCountRestricter = readCountRestricter;
  }

  @Override
  public String read() throws Exception, UnexpectedInputException,
      ParseException, NonTransientResourceException {
    long previousReadCount = readCountRestricter.getReadCount();
    if (readCountRestricter.getCountToProcess() <= previousReadCount) {
      return null;
    }
    String nextRecord = readRepository.readNext();
    if (nextRecord != null) {
      readCountRestricter.setReadCount(previousReadCount + 1);
    }
    return nextRecord;
  }
}
