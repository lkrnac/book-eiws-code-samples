package net.lkrnac.book.eiws.chapter02.rmi;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class ServiceConfigurationITest extends AbstractTestNGSpringContextTests {
  @Test
  public void contextLoads() {
  }
}
