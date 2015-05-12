package net.lkrnac.book.eiws.chapter05;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JavaJmsAsyncApplication.class)
public class JavaJmsAsyncApplicationTests {

  @Test
  public void contextLoads() throws InterruptedException {
    Thread.sleep(5000);
  }

}
