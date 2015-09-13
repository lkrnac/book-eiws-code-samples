package net.lkrnac.book.eiws.chapter09;

import static org.testng.Assert.assertEquals;

import java.util.stream.Stream;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;
import net.lkrnac.book.eiws.chapter09.step.TestExecutablePoint;
import net.lkrnac.book.eiws.chapter09.write.TestWriteRepository;
import net.lkrnac.book.eiws.chapter09.write.WriteRepository;

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
  private SimpleExecutablePoint executableStep;

  @Test(timeOut = 3000)
  public void testBatch() {
    // GIVEN - Spring configuration

    // WHEN - Spring Batch job is started automatically

    // THEN
    TestWriteRepository testWriteRepository =
        (TestWriteRepository) writeRepository;
    Stream.iterate(0, idx -> idx + 1)
        .map(idx -> "simple record " + idx + " processed")
        .limit(15)
        .forEach(
            exp -> Assert.assertEquals(testWriteRepository.getMessage(), exp));

    TestExecutablePoint testExecutablePoint =
        (TestExecutablePoint) executableStep;
    assertEquals(testExecutablePoint.getMessage(), "Opening records reader");
    assertEquals(testExecutablePoint.getMessage(), "Opening records writer");
    assertEquals(testExecutablePoint.getMessage(), "Updating records reader");
    assertEquals(testExecutablePoint.getMessage(), "Updating records writer");
    assertEquals(testExecutablePoint.getMessage(), "Updating records reader");
    assertEquals(testExecutablePoint.getMessage(), "Updating records writer");
    assertEquals(testExecutablePoint.getMessage(), "Updating records reader");
    assertEquals(testExecutablePoint.getMessage(), "Updating records writer");
    assertEquals(testExecutablePoint.getMessage(), "Updating records reader");
    assertEquals(testExecutablePoint.getMessage(), "Updating records writer");
    assertEquals(testExecutablePoint.getMessage(), "Updating records reader");
    assertEquals(testExecutablePoint.getMessage(), "Updating records writer");
    assertEquals(testExecutablePoint.getMessage(), "Closing records reader");
    assertEquals(testExecutablePoint.getMessage(), "Closing records writer");
  }
}
