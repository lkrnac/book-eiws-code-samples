package net.lkrnac.book.eiws.chapter09;

import static org.testng.Assert.assertEquals;
import net.lkrnac.book.eiws.chapter09.write.TestWriteRepository;
import net.lkrnac.book.eiws.chapter09.write.WriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ActiveProfiles("integration-test")
@SpringApplicationConfiguration(classes = BatchApplication.class)
public class BatchApplicationTest extends AbstractTestNGSpringContextTests {
  @Autowired
  private WriteRepository writeRepository;

  @Test(timeOut = 5000)
  public void testBatch() {
    // GIVEN - Spring configuration

    // WHEN - Spring Batch job is started automatically

    // THEN
    TestWriteRepository testWriteRepository =
        (TestWriteRepository) writeRepository;
    assertEquals(testWriteRepository.getMessage(), "simple record 0 processed");
    assertEquals(testWriteRepository.getMessage(), "simple record 4 processed");
    assertEquals(testWriteRepository.getMessage(), "simple record 5 processed");
    assertEquals(testWriteRepository.getMessage(), "simple record 6 processed");
    assertEquals(testWriteRepository.getMessage(), "simple record 7 processed");
    assertEquals(testWriteRepository.getMessage(), "simple record 8 processed");
    assertEquals(testWriteRepository.getMessage(), "simple record 12 processed");
    assertEquals(testWriteRepository.getMessage(), "simple record 13 processed");
    assertEquals(testWriteRepository.getMessage(), "simple record 14 processed");
  }
}
