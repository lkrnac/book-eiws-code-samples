package net.lkrnac.book.eiws.chapter08.out;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter08.out.WriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
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

  public void write(String message, @Headers Map<String, Object> defaultHeaders) {
    log.info("Message " + message + " received with headers: " + defaultHeaders);
    writeRepository.write(message);
  }
}
