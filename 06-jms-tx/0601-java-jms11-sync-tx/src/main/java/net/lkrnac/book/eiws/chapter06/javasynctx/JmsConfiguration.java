package net.lkrnac.book.eiws.chapter06.javasynctx;

import java.util.Hashtable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import lombok.Getter;

public class JmsConfiguration implements AutoCloseable {
  private InitialContext initialContext;

  @Getter
  private Queue queue;
  @Getter
  private Connection connection;

  public void init() throws NamingException, JMSException {
    Hashtable<Object, Object> env = new Hashtable<Object, Object>();
    env.put("java.naming.factory.initial",
        "org.jnp.interfaces.NamingContextFactory");
    env.put("java.naming.factory.url.pkgs",
        "org.jboss.naming:org.jnp.interfaces");
    env.put("java.naming.provider.url", "jnp://localhost:1099");
    initialContext = new InitialContext(env);

    queue = (Queue) initialContext.lookup("queue/ExpiryQueue");
    ConnectionFactory connectionFactory =
        (ConnectionFactory) initialContext.lookup("/ConnectionFactory");
    connection = connectionFactory.createConnection();
  }

  public void close() throws NamingException, JMSException {
    if (initialContext != null) {
      initialContext.close();
    }
    if (connection != null) {
      connection.close();
    }
  }
}
