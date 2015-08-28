package net.lkrnac.book.eiws.chapter08.out;

import static org.springframework.integration.IntegrationMessageHeaderAccessor.CORRELATION_ID;
import static org.springframework.integration.IntegrationMessageHeaderAccessor.SEQUENCE_NUMBER;
import static org.springframework.integration.IntegrationMessageHeaderAccessor.SEQUENCE_SIZE;

import java.util.Map;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WriteService {
  private WriteRepository writeRepository;

  @Autowired
  public WriteService(WriteRepository writeRepository) {
    super();
    this.writeRepository = writeRepository;
  }

  public boolean writeAndIndicateSuccess(String message,
      @Headers Map<String, Object> headers) {
    logHeaders(headers);

    boolean result = writeRepository.persist(message) == 1;
    if ("messageFail".equals(message)) {
      return false;
    }
    return result;
  }

  public void logHeaders(Map<String, Object> headers) {
    UUID correlationId = (UUID) headers.get(CORRELATION_ID);
    int sequenceSize = (int) headers.get(SEQUENCE_SIZE);
    int sequenceNumber = (int) headers.get(SEQUENCE_NUMBER);

    log.info("Received message headers: correlationId={}, "
        + "sequenceSize={}, sequenceNumber={}", correlationId, sequenceSize,
        sequenceNumber);
  }

}
