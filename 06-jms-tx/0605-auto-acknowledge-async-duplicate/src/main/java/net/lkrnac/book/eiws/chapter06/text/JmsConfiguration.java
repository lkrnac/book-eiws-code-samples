package net.lkrnac.book.eiws.chapter06.text;

import javax.jms.ConnectionFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jms.core.JmsTemplate;

//@Configuration
//@EnableJms
public class JmsConfiguration {
  // @Bean
  // public ConnectionFactory connectionFactory() {
  // return new ActiveMQConnectionFactory("vm://localhost");
  // }
  //
  // @Bean
  // public Queue queue() {
  // return new ActiveMQQueue("messageQueue");
  // }

  @Bean
  public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
    return new JmsTemplate(connectionFactory);
  }

  @Bean
  public DataSource dataSource() {
    // DriverManagerDataSource dataSource = new DriverManagerDataSource();
    // dataSource.setDriverClassName("org.h2.Driver");
    // dataSource.setUrl("jdbc:h2:file:h2\test");
    // dataSource.setUsername("sa");
    // dataSource.setPassword("");
    // return dataSource;
    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    return builder.setType(EmbeddedDatabaseType.H2).build();
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
