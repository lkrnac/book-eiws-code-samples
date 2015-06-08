package net.lkrnac.book.eiws.chapter06.text;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import lombok.extern.slf4j.Slf4j;

import org.springframework.jms.core.MessagePostProcessor;

@Slf4j
public class AcknowledgePostProcessor implements MessagePostProcessor {
  @Override
  public Message postProcessMessage(Message message) throws JMSException {
    log.info("Acknowledging send: " + ((TextMessage) message).getText());
    message.acknowledge();
    return message;
  }
}
