package net.lkrnac.book.eiws.chapter04.jaxrs;

import net.lkrnac.book.eiws.chapter04.jaxrs.Application;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationTests extends AbstractTestNGSpringContextTests {
  @Test
  public void contextLoads() {
  }
}
