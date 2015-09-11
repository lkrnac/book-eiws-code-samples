package net.lkrnac.book.eiws.chapter09;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutableStep;
import net.lkrnac.book.eiws.chapter09.step.TestExecutableStep;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(classes = BatchApplication.class)
public class BatchApplicationTest extends AbstractTestNGSpringContextTests {
  {
    System.setProperty("spring.profiles.active", "integration-test");
  }

  @Autowired
  private SimpleExecutableStep executableStep;

  @Autowired
  private JobLauncher jobLauncher;

  @Autowired
  private Job job;

  @Test(timeOut = 3000)
  public void testBatch() throws Exception {
    // GIVEN - Spring configuration

    // WHEN - Spring Batch job is started automatically
    JobExecution execution1 = jobLauncher.run(job, new JobParameters());
    JobExecution execution2 = jobLauncher.run(job, new JobParameters());
    JobExecution execution3 = jobLauncher.run(job, new JobParameters());

    // THEN
    TestExecutableStep testExecutableStep = (TestExecutableStep) executableStep;
    Assert.assertEquals(testExecutableStep.getMessage(), "Boil Water");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Tea");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Water");
    Assert.assertEquals(execution1.getStatus(), BatchStatus.COMPLETED);

    Assert.assertEquals(testExecutableStep.getMessage(), "Boil Water");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Tea");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Water");
    Assert.assertEquals(execution2.getStatus(), BatchStatus.COMPLETED);

    Assert.assertEquals(testExecutableStep.getMessage(), "Boil Water");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Tea");
    Assert.assertEquals(testExecutableStep.getMessage(),
        "Add Dirty Water (you should clean kettle with citric acid)");
    Assert.assertEquals(execution3.getStatus(), BatchStatus.COMPLETED);
  }
}
