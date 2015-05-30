package net.lkrnac.book.eiws.chapter05.pubsub;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PubSubService {
  public void handleMessage(int listenerId, String message) {
    log.info("Message Received: {} via listener {}", message, listenerId);
  }
}
