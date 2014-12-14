package net.lkrnac.book.eiws.chapter02.httpinvoker.servlet.xmlconfig.client;

import static org.testng.Assert.assertEquals;
import net.lkrnac.book.eiws.chapter02.httpinvoker.servlet.xmlconfig.shared.BarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "classpath:client-config.xml")
public class HttpInvokerServletXmlConfigITCase extends
    AbstractTestNGSpringContextTests {
  @Autowired
  private BarService barService;

  @Test(groups = "maventests")
  public void testHttpInvoker() {
    // GIVEN - client context

    // WHEN
    String actualResult = barService.serveBar("0205 Integration test");

    // THEN
    assertEquals(actualResult,
        "Bar service 0205 response to parameter: 0205 Integration test");
  }
}
