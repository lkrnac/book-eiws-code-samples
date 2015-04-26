package net.lkrnac.book.eiws.chapter05.jms11jndi;

import java.util.Hashtable;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class QueueJndiIT {
  private static final String MESSAGE_TEXT = "lalala";

  @Test(groups = "maventests")
  public void queueTest() throws NamingException, JMSException {
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

      // WHEN
      MessageSender messageSender = new MessageSender(jmsContext, queue);
      messageSender.sendMessage(MESSAGE_TEXT);

      MessageConsumer messageConsumer = new MessageConsumer(jmsContext, queue);
      String actualMessage = messageConsumer.readMessage();

      // THEN
      Assert.assertEquals(MESSAGE_TEXT, actualMessage);
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
