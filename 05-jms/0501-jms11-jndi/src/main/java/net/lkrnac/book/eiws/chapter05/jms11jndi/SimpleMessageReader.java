package net.lkrnac.book.eiws.chapter05.jms11jndi;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class SimpleMessageReader {
  private MessageConsumer messageConsumer;

  public SimpleMessageReader(Connection connection, Queue queue)
      throws JMSException {
    super();
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    messageConsumer = session.createConsumer(queue);
  }

  public String readMessage() throws JMSException {
    TextMessage messageReceived = (TextMessage) messageConsumer.receive(5000);
    return messageReceived.getText();
  }
}
