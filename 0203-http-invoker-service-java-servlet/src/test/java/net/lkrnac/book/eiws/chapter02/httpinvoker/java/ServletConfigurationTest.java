package net.lkrnac.book.eiws.chapter02.httpinvoker.java;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = ServletConfigurationTest.class)
@WebAppConfiguration
public class ServletConfigurationTest {

  @Test
  public void contextLoads() {
  }

}
