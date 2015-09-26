package net.lkrnac.book.eiws.chapter09;

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
  public Integer initDbTable() {
    jdbcTemplate.execute("drop table USERS if exists");
    jdbcTemplate
        .execute("create table USERS(NAME varchar(50), EMAIL varchar(50))");
    return null;
  }
}
