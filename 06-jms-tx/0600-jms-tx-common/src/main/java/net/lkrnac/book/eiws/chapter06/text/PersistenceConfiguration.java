package net.lkrnac.book.eiws.chapter06.text;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@Configuration
public class PersistenceConfiguration {
  @Bean
  JdbcTemplate jdbcTemplate(DataSource dataSource) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    log.info("Creating table TEXT_TABLE");
    jdbcTemplate.execute("drop table TEXT_TABLE if exists");
    jdbcTemplate.execute("create table TEXT_TABLE(TEXT varchar(30))");
    return jdbcTemplate;
  }
}
