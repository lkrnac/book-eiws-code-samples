package net.lkrnac.book.eiws.chapter08.out;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WriteServiceWithHeaders {
  private WriteRepository writeRepository;

  @Autowired
  public WriteServiceWithHeaders(WriteRepository writeRepository) {
    super();
    this.writeRepository = writeRepository;
  }

  public void write(String message, @Header String simpleHeader) {
    log.info("Writing message: {}; simpleHeader: {}", message, simpleHeader);
    writeRepository.persist(message);
  }
}
