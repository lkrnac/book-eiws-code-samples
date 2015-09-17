package net.lkrnac.book.eiws.chapter09;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;
import net.lkrnac.book.eiws.chapter09.step.TestExecutablePoint;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = BatchApplication.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BatchApplicationTest extends AbstractTestNGSpringContextTests {
  {
    System.setProperty("spring.profiles.active", "integration-test");
  }

  @Autowired
  private SimpleExecutablePoint executableStep;

  @Autowired
  private JobLauncher jobLauncher;

  @Autowired
  @Qualifier("prepareTeaJob")
  private Job prepareTeaJob;

  @Autowired
  @Qualifier("prepareTeaJobNotRestartable")
  private Job prepareTeaJobNotRestartable;

  @Test(timeOut = 5000)
  public void testRestartableJob_SingleExecution() throws Exception {
    // GIVEN

    // WHEN
    JobExecution execution1 =
        jobLauncher.run(prepareTeaJob, new JobParameters());

    // THEN
    TestExecutablePoint testExecutableStep =
        (TestExecutablePoint) executableStep;
    Assert.assertEquals(testExecutableStep.getMessage(), "Boil Water");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Tea");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Water");
    Assert.assertEquals(execution1.getStatus(), BatchStatus.COMPLETED);

    Assert.assertEquals(testExecutableStep.getMessage(1000), null);
  }

  @Test(timeOut = 5000)
  public void testRestartableJob_DoubleExecution() throws Exception {
    // GIVEN

    // WHEN
    JobExecution execution1 =
        jobLauncher.run(prepareTeaJob, new JobParameters());
    JobExecution execution2 =
        jobLauncher.run(prepareTeaJob, new JobParameters());

    // THEN
    TestExecutablePoint testExecutableStep =
        (TestExecutablePoint) executableStep;
    Assert.assertEquals(testExecutableStep.getMessage(), "Boil Water");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Tea");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Water");
    Assert.assertEquals(execution1.getStatus(), BatchStatus.COMPLETED);
    Assert.assertEquals(testExecutableStep.getMessage(), "Boil Water");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Tea");
    Assert.assertEquals(execution2.getStatus(), BatchStatus.COMPLETED);

    Assert.assertEquals(testExecutableStep.getMessage(1000), null);
  }

  @Test(timeOut = 5000)
  public void testRestartableJob_TripleExecution() throws Exception {
    // GIVEN

    // WHEN
    JobExecution execution1 =
        jobLauncher.run(prepareTeaJob, new JobParameters());
    JobExecution execution2 =
        jobLauncher.run(prepareTeaJob, new JobParameters());
    JobExecution execution3 =
        jobLauncher.run(prepareTeaJob, new JobParameters());

    // THEN
    TestExecutablePoint testExecutableStep =
        (TestExecutablePoint) executableStep;
    Assert.assertEquals(testExecutableStep.getMessage(), "Boil Water");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Tea");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Water");
    Assert.assertEquals(execution1.getStatus(), BatchStatus.COMPLETED);
    Assert.assertEquals(testExecutableStep.getMessage(), "Boil Water");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Tea");
    Assert.assertEquals(execution2.getStatus(), BatchStatus.COMPLETED);
    Assert.assertEquals(execution3.getStatus(), BatchStatus.FAILED);
  }

  @Test(timeOut = 5000)
  public void testNotRestartableJob_SingleExecution() throws Exception {
    // GIVEN

    // WHEN
    JobExecution execution1 =
        jobLauncher.run(prepareTeaJobNotRestartable, new JobParameters());

    // THEN
    TestExecutablePoint testExecutableStep =
        (TestExecutablePoint) executableStep;
    Assert.assertEquals(testExecutableStep.getMessage(), "Boil Water");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Tea");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Water");
    Assert.assertEquals(execution1.getStatus(), BatchStatus.COMPLETED);

    Assert.assertEquals(testExecutableStep.getMessage(1000), null);
  }

  @Test(timeOut = 5000, expectedExceptions = JobRestartException.class)
  public void testNotRestartableJob_DoubleExecution() throws Exception {
    // GIVEN

    // WHEN
    jobLauncher.run(prepareTeaJobNotRestartable, new JobParameters());
    jobLauncher.run(prepareTeaJobNotRestartable, new JobParameters());

    // THEN - see annotation attributes
  }

}
