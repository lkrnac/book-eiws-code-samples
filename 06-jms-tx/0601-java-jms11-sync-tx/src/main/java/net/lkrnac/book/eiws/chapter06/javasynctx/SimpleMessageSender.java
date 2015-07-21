package net.lkrnac.book.eiws.chapter06.javasynctx;

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
    session = connection.createSession(true, Session.SESSION_TRANSACTED);
    messageProducer = session.createProducer(queue);
  }

  public void sendMessages(String message1, String message2)
      throws JMSException {
    try {
      TextMessage textMessage1 = session.createTextMessage(message1);
      messageProducer.send(textMessage1);
      TextMessage textMessage2 = session.createTextMessage(message2);
      messageProducer.send(textMessage2);
      session.commit();
    } catch (Throwable throwable) {
      session.rollback();
    }
  }
}
