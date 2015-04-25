package org.hornetq.test;

import java.util.Hashtable;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Test;

public class QueueJndiIT {
  private static final String MESSAGE_TEXT = "lalala";

  @Test
  public void queueTest() throws NamingException {
    InitialContext initialContext = null;
    JMSContext jmsContext = null;
    try {
      Hashtable<Object, Object> env = new Hashtable<Object, Object>();
      env.put("java.naming.factory.initial",
          "org.jnp.interfaces.NamingContextFactory");
      env.put("java.naming.factory.url.pkgs",
          "org.jboss.naming:org.jnp.interfaces");
      env.put("java.naming.provider.url", "jnp://localhost:1099");
      initialContext = new InitialContext(env);

      Queue queue = (Queue) initialContext.lookup("queue/messageQueue");
      ConnectionFactory cf =
          (ConnectionFactory) initialContext.lookup("/ConnectionFactory");
      jmsContext = cf.createContext();
      jmsContext.createProducer().send(queue, MESSAGE_TEXT);

      JMSConsumer jmsConsumer = jmsContext.createConsumer(queue);

      TextMessage messageReceived = (TextMessage) jmsConsumer.receive(5000);

      Assert.assertEquals(MESSAGE_TEXT, messageReceived.getText());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (initialContext != null) {
        initialContext.close();
      }
      if (jmsContext != null) {
        jmsContext.close();
      }
    }
  }
}
