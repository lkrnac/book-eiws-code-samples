package net.lkrnac.book.eiws.chapter08;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter08.in.SiWrapperServiceTransacted;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@SpringBootApplication
@ImportResource("classpath:si-config.xml")
public class SiApplication {
  private static final String SELECT_COUNT = "select count(*) from TEXT_TABLE";

  public static void main(String[] args) throws InterruptedException {
    ApplicationContext ctx = SpringApplication.run(SiApplication.class, args);

    try {
      SiWrapperServiceTransacted wrapperService =
          ctx.getBean(SiWrapperServiceTransacted.class);
      wrapperService.processText("simple message");
    } catch (IllegalStateException ise) {
      JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
      int recordCount =
          jdbcTemplate.queryForObject(SELECT_COUNT, Integer.class);
      log.info("Record count: " + recordCount);
    }
  }
}
