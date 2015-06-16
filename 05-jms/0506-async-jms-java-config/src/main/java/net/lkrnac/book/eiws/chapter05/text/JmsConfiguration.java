package net.lkrnac.book.eiws.chapter05.text;

import java.util.Hashtable;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JmsConfiguration {
  @Bean
  public InitialContext initialContext() throws NamingException {
    Hashtable<Object, Object> env = new Hashtable<Object, Object>();
    env.put("java.naming.factory.initial",
        "org.jnp.interfaces.NamingContextFactory");
    env.put("java.naming.factory.url.pkgs",
        "org.jboss.naming:org.jnp.interfaces");
    env.put("java.naming.provider.url", "jnp://localhost:1099");
    return new InitialContext(env);
  }

  @Bean
  public ConnectionFactory connectionFactory(InitialContext initialContext)
      throws NamingException {
    return (ConnectionFactory) initialContext.lookup("/ConnectionFactory");
  }

  @Bean
  public Queue queue(InitialContext initialContext) throws NamingException {
    return (Queue) initialContext.lookup("/queue/ExpiryQueue");
  }

  @Bean
  public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
    return new JmsTemplate(connectionFactory);
  }
}
