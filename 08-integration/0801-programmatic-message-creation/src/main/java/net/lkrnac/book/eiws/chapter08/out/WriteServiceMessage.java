package net.lkrnac.book.eiws.chapter08.out;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WriteServiceMessage {
  private WriteRepositoryWithHeaders writeRepository;

  @Autowired
  public WriteServiceMessage(WriteRepositoryWithHeaders writeRepository) {
    super();
    this.writeRepository = writeRepository;
  }

  public void write(Message<String> message) {
    log.info("Message: " + message);
    String simpleHeader = (String) message.getHeaders().get("simpleHeader");
    writeRepository.writeWithHeader(message.getPayload(), simpleHeader);
  }
}
