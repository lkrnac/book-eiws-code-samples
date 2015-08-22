package net.lkrnac.book.eiws.chapter08.out;

import java.util.Date;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
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

  public void write(String message, @Header("timestamp") long creationTime,
      @Headers Map<String, Object> headers) {
    log.info("Message received with payload: {}; Created: {}; Headers: {}",
        message, new Date(creationTime), headers);
    writeRepository.persist(message);
  }
}
