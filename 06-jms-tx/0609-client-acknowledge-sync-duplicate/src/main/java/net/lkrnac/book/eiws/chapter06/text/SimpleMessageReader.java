package net.lkrnac.book.eiws.chapter06.text;

import javax.jms.JMSException;
import javax.jms.Message;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleMessageReader {
  private JmsTemplate jmsTemplate;
  private SimpleService simpleService;
  private boolean errorSimulated = false;

  @Autowired
  public SimpleMessageReader(JmsTemplate jmsTemplate,
      SimpleService simpleService) {
    super();
    this.jmsTemplate = jmsTemplate;
    this.simpleService = simpleService;
  }

  @Scheduled(fixedRate = Long.MAX_VALUE)
  public void readMessage() throws JMSException {
    Message message = jmsTemplate.receive("messageQueue");
    String messageText = message.getBody(String.class);
    log.info("Message read: {}", messageText);

    simpleService.processText(messageText);
    postprocess(messageText);

    log.info("Acknowledging reception: " + messageText);
    message.acknowledge();
  }

  private void postprocess(String message) {
    // simualte error
    if (!errorSimulated) {
      errorSimulated = true;
      throw new IllegalArgumentException(message);
    }
  }
}
