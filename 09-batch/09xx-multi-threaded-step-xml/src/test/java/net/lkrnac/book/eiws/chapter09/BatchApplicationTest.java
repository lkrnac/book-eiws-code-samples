package net.lkrnac.book.eiws.chapter09;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

  @Test
  // (timeOut = 5000)
  public void testBatch() throws InterruptedException {
    // GIVEN - Spring configuration
    List<String> expectedRecords = IntStream.range(0, 15)
        .mapToObj(idx -> "simple record " + idx + " processed")
        .sorted()
        .collect(Collectors.toList());

    // WHEN - Spring Batch job is started automatically, but wait for all the
    // threads to complete chunking
    // Thread.sleep(10000);

    // THEN
    TestWriteRepository testWriteRepository =
        (TestWriteRepository) writeRepository;
    // List<String> actualRecords = IntStream.range(0, 15)
    // .mapToObj(testWriteRepository::getMessage)
    // .sorted()
    // .collect(Collectors.toList());
    List<String> actualRecords = Stream.iterate(0, idx -> idx + 1)
        .map(idx -> testWriteRepository.getMessage())
        .limit(15)
        .collect(Collectors.toList());

    Assert.assertEquals(actualRecords, expectedRecords);
  }
}
