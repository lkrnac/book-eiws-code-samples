package net.lkrnac.book.eiws.chapter02.hessian.dispatcherservlet;

import static org.testng.Assert.assertEquals;
import net.lkrnac.book.eiws.chapter02.hessian.dispatcherservlet.client.ClientConfiguration;
import net.lkrnac.book.eiws.chapter02.hessian.dispatcherservlet.shared.BarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = { ClientConfiguration.class })
public class HessianDispatcherServletITCase extends
    AbstractTestNGSpringContextTests {
  @Autowired
  private BarService barService;

  @Test(groups = "maventests")
  public void testHessian() {
    // GIVEN - client context

    // WHEN
    String actualResult = barService.serveBar("0210 Integration test");

    // THEN
    assertEquals(actualResult,
        "Bar service 0210 response to parameter: 0210 Integration test");
  }
}
