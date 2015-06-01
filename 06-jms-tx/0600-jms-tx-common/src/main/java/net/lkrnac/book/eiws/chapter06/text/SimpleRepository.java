package net.lkrnac.book.eiws.chapter06.text;

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

  public void persistText(String text) {
    jdbcTemplate.update("insert into TEXT_TABLE values (?)", text);
  }
}
