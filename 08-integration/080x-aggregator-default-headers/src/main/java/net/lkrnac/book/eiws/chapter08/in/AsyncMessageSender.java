package net.lkrnac.book.eiws.chapter08.in;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AsyncMessageSender {
  private SiWrapperServiceFutureAnnotated wrapperService;

  @Autowired
  public AsyncMessageSender(SiWrapperServiceFutureAnnotated wrapperService) {
    super();
    this.wrapperService = wrapperService;
  }

  @Async("customTaskExecutor")
  public void sendMessage(String stringMessage, int correlationId)
      throws Exception {
    Message<String> message =
        MessageBuilder.withPayload(stringMessage).setSequenceSize(2)
            .setCorrelationId(correlationId).build();

    Future<Boolean> resultFuture = wrapperService.processText(message);
    boolean result = resultFuture.get(1, TimeUnit.SECONDS);
    log.info("Result for " + stringMessage + ": " + result);
  }
}
