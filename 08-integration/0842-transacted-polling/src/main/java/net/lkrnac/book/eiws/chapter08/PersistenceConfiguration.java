package net.lkrnac.book.eiws.chapter08;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class PersistenceConfiguration {
  @Bean
  public Integer initDbTable(JdbcTemplate jdbcTemplate) {
    jdbcTemplate.execute("drop table TEXT_TABLE if exists");
    jdbcTemplate.execute("create table TEXT_TABLE(TEXT varchar(30))");
    return null;
  }
}
