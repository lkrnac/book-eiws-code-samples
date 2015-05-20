package net.lkrnac.book.eiws.chapter05.jms11jndi;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class SimpleMessageSender {
  private MessageProducer messageProducer;
  private Session session;

  public void init(Connection connection, Queue queue) throws JMSException {
    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    messageProducer = session.createProducer(queue);
  }

  public void sendMessage(String message) throws JMSException {
    TextMessage textMessage = session.createTextMessage(message);
    messageProducer.send(textMessage);
  }
}
