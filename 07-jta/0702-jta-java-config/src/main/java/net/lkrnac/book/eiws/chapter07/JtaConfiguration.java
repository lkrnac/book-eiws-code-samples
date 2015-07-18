package net.lkrnac.book.eiws.chapter07;

import java.util.Hashtable;

import javax.jms.ConnectionFactory;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.boot.jta.atomikos.AtomikosXADataSourceWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

@Configuration
@ComponentScan
@EnableJms
@EnableScheduling
@EnableTransactionManagement(proxyTargetClass = false)
public class JtaConfiguration {
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
    return (ConnectionFactory) initialContext.lookup("/XAConnectionFactory");
  }

  @Bean
  public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
    return new JmsTemplate(connectionFactory);
  }

  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
      ConnectionFactory connectionFactory) {
    DefaultJmsListenerContainerFactory factory =
        new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setSessionTransacted(true);
    factory.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
    return factory;
  }

  @Bean
  public DataSource dataSource() throws Exception {
    JdbcDataSource h2DataSource = new JdbcDataSource();
    h2DataSource
        .setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false");
    h2DataSource.setUser("sa");
    AtomikosXADataSourceWrapper wrapper = new AtomikosXADataSourceWrapper();
    return wrapper.wrapDataSource(h2DataSource);
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean(initMethod = "init", destroyMethod = "close")
  public UserTransactionManager atomikosTransactionManager() {
    UserTransactionManager atomikosTransationManager =
        new UserTransactionManager();
    atomikosTransationManager.setForceShutdown(true);
    // atomikosTransationManager.setTransactionTimeout(600);
    return atomikosTransationManager;
  }

  @Bean
  public JtaTransactionManager transactionManager(
      UserTransactionManager atomikosTransactionManager) {
    JtaTransactionManager transactionManager = new JtaTransactionManager();
    transactionManager.setTransactionManager(atomikosTransactionManager);
    transactionManager.setUserTransaction(new UserTransactionImp());
    return transactionManager;
  }
}
