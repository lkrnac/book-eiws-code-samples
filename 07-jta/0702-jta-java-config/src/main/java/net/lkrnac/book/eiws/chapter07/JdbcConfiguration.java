package net.lkrnac.book.eiws.chapter07;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.boot.jta.atomikos.AtomikosXADataSourceWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JdbcConfiguration {
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
}
