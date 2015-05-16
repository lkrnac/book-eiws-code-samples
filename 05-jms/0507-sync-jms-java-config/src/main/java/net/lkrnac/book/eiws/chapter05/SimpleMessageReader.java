package net.lkrnac.book.eiws.chapter05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageReader {
  private JmsTemplate jmsTemplate;
  private static final Logger LOG = LoggerFactory
      .getLogger(SimpleMessageReader.class);

  @Autowired
  public SimpleMessageReader(JmsTemplate jmsTemplate) {
    super();
    this.jmsTemplate = jmsTemplate;
  }

  @Scheduled(fixedRate = 1200L)
  public void readMessage() {
    String message = (String) jmsTemplate.receiveAndConvert("messageQueue");
    LOG.info("Received message: {}", message);
  }
}
