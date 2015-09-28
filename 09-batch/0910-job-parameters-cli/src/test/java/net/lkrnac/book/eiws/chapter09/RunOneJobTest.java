package net.lkrnac.book.eiws.chapter09;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;
import net.lkrnac.book.eiws.chapter09.step.TestExecutablePoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ActiveProfiles("integration-test")
@SpringApplicationConfiguration(classes = BatchApplication.class)
@DirtiesContext
public class RunOneJobTest extends AbstractTestNGSpringContextTests {
  {
    System.setProperty("spring.batch.job.names", "prepareTeaJob");
  }

  @Autowired
  private SimpleExecutablePoint executableStep;

  @Test(timeOut = 5000)
  public void testBatch() throws Exception {
    // GIVEN - Spring configuration

    // WHEN

    // THEN
    TestExecutablePoint testExecutableStep =
        (TestExecutablePoint) executableStep;
    Assert.assertEquals(testExecutableStep.getMessage(), "Boil Water");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Tea");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Water");
    Assert.assertEquals(testExecutableStep.getMessage(1000), null);
  }
}
