package net.lkrnac.book.eiws.chapter06.text;

import javax.jms.JMSException;
import javax.jms.Message;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleMessageListener {
  private SimpleService simpleService;
  private boolean errorSimulated = false;

  @Autowired
  public SimpleMessageListener(SimpleService simpleService) {
    super();
    this.simpleService = simpleService;
  }

  @JmsListener(destination = "messageQueue")
  public void readMessageDuplicate(String messageText, Message message)
      throws JMSException {
    simpleService.processText(messageText);
    if (!errorSimulated) {
      errorSimulated = true;
      throw new IllegalArgumentException(messageText);
    }
    log.info("Acknowledging reception: " + messageText);
    message.acknowledge();
  }
}
