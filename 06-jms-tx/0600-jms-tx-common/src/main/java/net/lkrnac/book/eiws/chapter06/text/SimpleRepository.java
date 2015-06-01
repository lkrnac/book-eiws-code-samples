package net.lkrnac.book.eiws.chapter06.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SimpleRepository {
  private final JdbcTemplate jdbcTemplate;
  private static final String SELECT_COUNT =
      "select count(*) from TEXT_TABLE where text = ?";

  @Autowired
  public SimpleRepository(JdbcTemplate jdbcTemplate) {
    super();
    this.jdbcTemplate = jdbcTemplate;
  }

  public void persistText(String text) {
    jdbcTemplate.update("insert into TEXT_TABLE values (?)", text);
  }

  public boolean containsText(String text) {
    long count = jdbcTemplate.queryForObject(SELECT_COUNT, Long.class, text);
    return count != 0;
  }
}
