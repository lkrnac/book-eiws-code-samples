package net.lkrnac.book.eiws.chapter09;

import java.util.stream.Stream;

import net.lkrnac.book.eiws.chapter09.write.TestWriteRepository;
import net.lkrnac.book.eiws.chapter09.write.WriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = BatchApplication.class,
    locations = "classpath:batch-slave-config.xml")
public class BatchApplicationIT extends AbstractTestNGSpringContextTests {
  {
    System.setProperty("spring.profiles.active", "integration-test");
  }

  @Autowired
  private WriteRepository writeRepository;

  @Test(timeOut = 10000)
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
  }
}
