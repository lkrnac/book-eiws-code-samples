package net.lkrnac.book.eiws.chapter05.asyncjndi;

import java.util.Hashtable;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JmsConfiguration implements AutoCloseable {
  private InitialContext initialContext;
  private JMSContext jmsContext;
  private Queue queue;
  private SimpleMessageHandler messageHandler;

  public JmsConfiguration(SimpleMessageHandler messageHandler) {
    super();
    this.messageHandler = messageHandler;
  }

  public void init() throws NamingException {
    Hashtable<Object, Object> env = new Hashtable<Object, Object>();
    env.put("java.naming.factory.initial",
        "org.jnp.interfaces.NamingContextFactory");
    env.put("java.naming.factory.url.pkgs",
        "org.jboss.naming:org.jnp.interfaces");
    env.put("java.naming.provider.url", "jnp://localhost:1099");
    initialContext = new InitialContext(env);

    queue = (Queue) initialContext.lookup("queue/messageQueue");
    ConnectionFactory cf =
        (ConnectionFactory) initialContext.lookup("/ConnectionFactory");
    jmsContext = cf.createContext();

    JMSContext consumerContext = cf.createContext();
    JMSConsumer jmsConsumer = consumerContext.createConsumer(queue);
    MessageListener messageListener = new SimpleMessageListener(messageHandler);
    jmsConsumer.setMessageListener(messageListener);
  }

  public void close() throws NamingException {
    if (initialContext != null) {
      initialContext.close();
    }
    if (jmsContext != null) {
      jmsContext.close();
    }
  }

  public Queue getQueue() {
    return queue;
  }

  public JMSContext getJmsContext() {
    return jmsContext;
  }

}
