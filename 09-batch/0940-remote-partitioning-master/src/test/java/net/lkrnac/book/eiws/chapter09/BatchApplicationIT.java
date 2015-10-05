package net.lkrnac.book.eiws.chapter09;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter09.write.TestWriteRepository;
import net.lkrnac.book.eiws.chapter09.write.WriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
@ActiveProfiles("integration-test")
@SpringApplicationConfiguration(classes = BatchApplication.class,
    locations = { "classpath:batch-slave-config.xml", "classpath:sql-server-config.xml" })
public class BatchApplicationIT extends AbstractTestNGSpringContextTests {
  @Autowired
  private WriteRepository writeRepository;

  @Test(timeOut = 10000)
  public void testBatch() {
    // GIVEN - Spring configuration
    List<String> expectedRecords = IntStream.range(0, 16)
        .mapToObj(idx -> "simple record " + idx + " processed")
        .sorted()
        .collect(Collectors.toList());

    // WHEN - Spring Batch job is started automatically

    // THEN
    TestWriteRepository testWriteRepository =
        (TestWriteRepository) writeRepository;

    List<String> actualRecords = IntStream.range(0, 16)
        .mapToObj(testWriteRepository::getMessage)
        .sorted()
        .peek(log::info)
        .collect(Collectors.toList());
    Assert.assertEquals(actualRecords, expectedRecords);
  }
}
