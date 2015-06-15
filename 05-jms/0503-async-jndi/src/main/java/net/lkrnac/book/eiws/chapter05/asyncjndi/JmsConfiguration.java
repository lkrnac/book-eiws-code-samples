package net.lkrnac.book.eiws.chapter05.asyncjndi;

import java.util.Hashtable;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import lombok.Getter;
import net.lkrnac.book.eiws.chapter05.text.SimpleService;

public class JmsConfiguration implements AutoCloseable {
  private InitialContext initialContext;
  private SimpleService simpleService;

  @Getter
  private JMSContext jmsContext;
  @Getter
  private Queue queue;

  public JmsConfiguration(SimpleService simpleService) {
    super();
    this.simpleService = simpleService;
  }

  public void init() throws NamingException {
    Hashtable<Object, Object> env = new Hashtable<Object, Object>();
    env.put("java.naming.factory.initial",
        "org.jnp.interfaces.NamingContextFactory");
    env.put("java.naming.factory.url.pkgs",
        "org.jboss.naming:org.jnp.interfaces");
    env.put("java.naming.provider.url", "jnp://localhost:1099");
    initialContext = new InitialContext(env);

    queue = (Queue) initialContext.lookup("queue/ExpiryQueue");
    ConnectionFactory cf =
        (ConnectionFactory) initialContext.lookup("/ConnectionFactory");
    jmsContext = cf.createContext();

    JMSConsumer jmsConsumer = jmsContext.createConsumer(queue);
    MessageListener messageListener = new SimpleMessageListener(simpleService);
    jmsConsumer.setMessageListener(messageListener);
  }

  @Override
  public void close() throws NamingException {
    if (initialContext != null) {
      initialContext.close();
    }
    if (jmsContext != null) {
      jmsContext.close();
    }
  }
}
