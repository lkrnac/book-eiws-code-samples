package net.lkrnac.book.eiws.chapter08;

import net.lkrnac.book.eiws.chapter09.BatchApplication;
import net.lkrnac.book.eiws.chapter09.step.SimpleExecutableStep;
import net.lkrnac.book.eiws.chapter09.step.TestExecutableStep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = BatchApplication.class)
public class BatchApplicationTest extends AbstractTestNGSpringContextTests {
  {
    System.setProperty("spring.profiles.active", "integration-test");
  }

  @Autowired
  private SimpleExecutableStep executableStep;

  @Test(timeOut = 3000)
  public void testBatch() {
    // GIVEN - Spring configuration

    // WHEN - Spring Batch job is started automatically

    // THEN
    TestExecutableStep testExecutableStep = (TestExecutableStep) executableStep;
    Assert.assertEquals(testExecutableStep.getMessage(), "Boil Water");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Tea");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Water");
  }
}
