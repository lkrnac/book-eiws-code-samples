package net.lkrnac.book.eiws.chapter09;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class PersistenceConfiguration {
  @Bean
  public Integer initDbTable(JdbcTemplate jdbcTemplate) {
    jdbcTemplate.execute("drop table USERS if exists");
    jdbcTemplate
        .execute("create table USERS(NAME varchar(50), EMAIL varchar(50))");
    return null;
  }
}
