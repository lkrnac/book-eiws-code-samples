package net.lkrnac.book.eiws.chapter02.hessian.handlerservlet.javaconfig;

import static org.testng.Assert.assertEquals;
import net.lkrnac.book.eiws.chapter02.hessian.handlerservlet.javaconfig.client.ClientConfiguration;
import net.lkrnac.book.eiws.chapter02.hessian.handlerservlet.javaconfig.shared.BarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = { ClientConfiguration.class })
public class HessianHandlerServletJavaConfigITCase extends
    AbstractTestNGSpringContextTests {
  @Autowired
  private BarService barService;

  @Test(groups = "maventests")
  public void testHessian() {
    // GIVEN - client context

    // WHEN
    String actualResult = barService.serveBar("0208 Integration test");

    // THEN
    assertEquals(actualResult,
        "Bar service 0208 response to parameter: 0208 Integration test");
  }
}
