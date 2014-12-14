package net.lkrnac.book.eiws.chapter02.httpinvoker.simple;

import net.lkrnac.book.eiws.chapter02.httpinvoker.simple.server.ServerConfiguration;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = { ServerConfiguration.class })
public class ServerConfigurationTest extends AbstractTestNGSpringContextTests {
  @Test
  public void testContextloads() {
  }
}
