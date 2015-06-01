package net.lkrnac.book.eiws.chapter06.javaasynctx;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter05.text.SimpleService;

@Slf4j
public class SimpleMessageListener implements MessageListener {
  private SimpleService simpleService;
  private JMSContext jmsContext;

  public SimpleMessageListener(SimpleService simpleService,
      JMSContext jmsContext) {
    super();
    this.simpleService = simpleService;
    this.jmsContext = jmsContext;
  }

  @Override
  public void onMessage(Message message) {
    try {
      String textMessage = message.getBody(String.class);
      simpleService.processText(textMessage);
      jmsContext.commit();
    } catch (JMSException jmse) {
      log.error("Error during message reception", jmse);
    }
  }
}
