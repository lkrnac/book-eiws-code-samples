package net.lkrnac.book.eiws.chapter09;

import static org.testng.Assert.assertEquals;

import java.util.stream.IntStream;

import net.lkrnac.book.eiws.chapter09.write.TestWriteRepository;
import net.lkrnac.book.eiws.chapter09.write.WriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ActiveProfiles("integration-test")
@SpringApplicationConfiguration(classes = BatchApplication.class,
    locations = "classpath:batch-slave-config.xml")
public class BatchApplicationIT extends AbstractTestNGSpringContextTests {
  @Autowired
  private WriteRepository writeRepository;

  @Test(timeOut = 10000)
  public void testBatch() {
    // GIVEN - Spring configuration

    // WHEN - Spring Batch job is started automatically

    // THEN
    TestWriteRepository testWriteRepository =
        (TestWriteRepository) writeRepository;
    IntStream.range(0, 15)
        .mapToObj(idx -> "simple record " + idx + " processed")
        .forEach(exp -> assertEquals(testWriteRepository.getMessage(), exp));
  }
}
