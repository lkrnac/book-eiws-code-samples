package net.lkrnac.book.eiws.chapter09;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;
import net.lkrnac.book.eiws.chapter09.step.TestExecutablePoint;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ActiveProfiles("integration-test")
@SpringApplicationConfiguration(classes = BatchApplication.class)
public class BatchApplicationTest extends AbstractTestNGSpringContextTests {
  @Autowired
  private SimpleExecutablePoint executableStep;

  @Autowired
  private JobLauncher jobLauncher;

  @Autowired
  private Job job;

  @Test(timeOut = 5000)
  public void testBatch_WithSugar() throws Exception {
    // GIVEN
    JobParameters jobParameters = new JobParametersBuilder()
        .addLong("sugarAmount", 2L)
        .toJobParameters();

    // WHEN
    JobExecution jobExecution = jobLauncher.run(job, jobParameters);

    // THEN
    TestExecutablePoint testExecutableStep =
        (TestExecutablePoint) executableStep;
    Assert.assertEquals(testExecutableStep.getMessage(), "Boil Water");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Tea");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Water");
    Assert.assertEquals(testExecutableStep.getMessage(),
        "Add one spoon of sugar");
    Assert.assertEquals(testExecutableStep.getMessage(),
        "Add one spoon of sugar");
    Assert.assertEquals(jobExecution.getExitStatus(), ExitStatus.COMPLETED);
  }

  @Test(timeOut = 5000)
  public void testBatch_NoSugar() throws Exception {
    // GIVEN
    JobParameters jobParameters = new JobParametersBuilder()
        .addLong("sugarAmount", 0L)
        .toJobParameters();

    // WHEN
    JobExecution jobExecution = jobLauncher.run(job, jobParameters);

    // THEN
    TestExecutablePoint testExecutableStep =
        (TestExecutablePoint) executableStep;
    Assert.assertEquals(testExecutableStep.getMessage(), "Boil Water");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Tea");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Water");
    Assert.assertEquals(jobExecution.getExitStatus(), ExitStatus.COMPLETED);
  }
}
