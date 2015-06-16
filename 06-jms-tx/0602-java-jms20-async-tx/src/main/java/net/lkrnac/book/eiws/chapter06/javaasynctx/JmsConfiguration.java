package net.lkrnac.book.eiws.chapter06.javaasynctx;

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
  private JMSContext readContext;

  @Getter
  private JMSContext writeContext;
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

    queue = (Queue) initialContext.lookup("/queue/ExpiryQueue");
    ConnectionFactory cf =
        (ConnectionFactory) initialContext.lookup("/ConnectionFactory");
    readContext = cf.createContext(JMSContext.SESSION_TRANSACTED);
    writeContext = cf.createContext(JMSContext.SESSION_TRANSACTED);

    JMSConsumer jmsConsumer = readContext.createConsumer(queue);
    MessageListener messageListener =
        new SimpleMessageListener(simpleService, readContext);
    jmsConsumer.setMessageListener(messageListener);
  }

  public void close() throws NamingException {
    if (initialContext != null) {
      initialContext.close();
    }
    if (readContext != null) {
      readContext.close();
    }
    if (writeContext != null) {
      writeContext.close();
    }
  }
}
