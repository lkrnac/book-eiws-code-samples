package net.lkrnac.book.eiws.chapter08;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SimpleDatabasePopulator {
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public SimpleDatabasePopulator(JdbcTemplate jdbcTemplate) {
    super();
    this.jdbcTemplate = jdbcTemplate;
  }

  @PostConstruct
  public void initDbTable() {
    jdbcTemplate.execute("drop table TEXT_TABLE if exists");
    jdbcTemplate.execute("create table TEXT_TABLE(TEXT varchar(30))");
  }
}
