package net.lkrnac.book.eiws.chapter09;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;
import net.lkrnac.book.eiws.chapter09.step.TestExecutablePoint;
import net.lkrnac.book.eiws.chapter09.write.TestWriteRepository;
import net.lkrnac.book.eiws.chapter09.write.WriteRepository;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
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
  private WriteRepository writeRepository;

  @Autowired
  private JobLauncher jobLauncher;

  @Autowired
  private Job job;

  @Autowired
  private SimpleExecutablePoint executableStep;

  @Test(timeOut = 5000)
  public void testBatch() throws Exception {
    // GIVEN
    Map<String, JobParameter> jobParametersMap = new HashMap<>();
    jobParametersMap.put("recordCountToProcess", new JobParameter(11L));
    JobParameters jobParameters = new JobParameters(jobParametersMap);

    // WHEN
    JobExecution execution = jobLauncher.run(job, jobParameters);

    // THEN
    TestWriteRepository testWriteRepository =
        (TestWriteRepository) writeRepository;
    Stream.iterate(0, idx -> idx + 1)
        .map(idx -> "simple record " + idx + " processed")
        .limit(11)
        .forEach(
            exp -> Assert.assertEquals(testWriteRepository.getMessage(), exp));

    Assert.assertEquals(testWriteRepository.getMessage(1000), null);

    TestExecutablePoint testExecutableStep =
        (TestExecutablePoint) executableStep;
    Assert.assertEquals(testExecutableStep.getMessage(),
        "After we processed 11 records, let's have a tea");
    Assert.assertEquals(testExecutableStep.getMessage(), "Boil Water");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Tea");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Water");
    Assert.assertEquals(execution.getExitStatus(), ExitStatus.COMPLETED);
  }
}
