package net.lkrnac.book.eiws.chapter02.httpinvoker.java;

import net.lkrnac.book.eiws.chapter02.httpinvoker.java.Application;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationTests {

  @Test
  public void contextLoads() {
  }

}
