package net.lkrnac.book.eiws.chapter07;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SimpleRepository {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public SimpleRepository(JdbcTemplate jdbcTemplate) {
    super();
    this.jdbcTemplate = jdbcTemplate;
  }

  @PostConstruct
  public void initDbTable() {
    jdbcTemplate.execute("drop table TEXT_TABLE if exists");
    jdbcTemplate.execute("create table TEXT_TABLE(TEXT varchar(30))");
  }

  public void persistText(String text) {
    jdbcTemplate.update("insert into TEXT_TABLE values (?)", text);
  }
}
