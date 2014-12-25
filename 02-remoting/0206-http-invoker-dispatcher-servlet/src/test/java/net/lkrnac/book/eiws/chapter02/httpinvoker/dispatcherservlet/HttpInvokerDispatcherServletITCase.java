package net.lkrnac.book.eiws.chapter02.httpinvoker.dispatcherservlet;

import static org.testng.Assert.assertEquals;
import net.lkrnac.book.eiws.chapter02.httpinvoker.dispatcherservlet.client.ClientConfiguration;
import net.lkrnac.book.eiws.chapter02.httpinvoker.dispatcherservlet.shared.BarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = { ClientConfiguration.class })
public class HttpInvokerDispatcherServletITCase extends AbstractTestNGSpringContextTests {
  @Autowired
  private BarService barService;

  @Test(groups = "maventests")
  public void testHttpInvoker() {
    // GIVEN - client context

    // WHEN
    String actualResult = barService.serveBar("0206 Integration test");

    // THEN
    assertEquals(actualResult,
        "Bar service 0206 response to parameter: 0206 Integration test");
  }
}
