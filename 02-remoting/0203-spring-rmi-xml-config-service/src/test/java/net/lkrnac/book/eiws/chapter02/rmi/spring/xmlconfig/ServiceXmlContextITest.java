package net.lkrnac.book.eiws.chapter02.rmi.spring.xmlconfig;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "classpath:bar-service-context.xml")
public class ServiceXmlContextITest extends AbstractTestNGSpringContextTests {
  @Test
  public void testContextLoads() {
    // no logic needed
  }
}
