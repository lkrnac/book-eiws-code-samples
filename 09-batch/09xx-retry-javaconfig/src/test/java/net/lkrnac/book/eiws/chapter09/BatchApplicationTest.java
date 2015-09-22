package net.lkrnac.book.eiws.chapter09;

import static org.testng.Assert.assertEquals;

import java.util.stream.IntStream;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;
import net.lkrnac.book.eiws.chapter09.step.TestExecutablePoint;
import net.lkrnac.book.eiws.chapter09.write.TestWriteRepository;
import net.lkrnac.book.eiws.chapter09.write.WriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = BatchApplication.class)
public class BatchApplicationTest extends AbstractTestNGSpringContextTests {
  {
    System.setProperty("spring.profiles.active", "integration-test");
  }

  @Autowired
  private WriteRepository writeRepository;

  @Autowired
  private SimpleExecutablePoint executableStep;

  @Test(timeOut = 5000)
  public void testBatch() {
    // GIVEN - Spring configuration

    // WHEN - Spring Batch job is started automatically

    // THEN
    TestWriteRepository testWriteRepository =
        (TestWriteRepository) writeRepository;
    IntStream.range(0, 15)
        .mapToObj(idx -> "simple record " + idx + " processed")
        .forEach(exp -> assertEquals(testWriteRepository.getMessage(), exp));

    TestExecutablePoint testExecutableStep =
        (TestExecutablePoint) executableStep;
    assertEquals(testExecutableStep.getMessage(),
        "Error occured, retrying write of items");
  }
}
