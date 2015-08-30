package net.lkrnac.book.eiws.chapter08.out;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ServiceWithError {
  public boolean handleJdbcResult(Map<String, Object> jdbcResult) {
    throw new IllegalStateException("error occurred");
  }
}
