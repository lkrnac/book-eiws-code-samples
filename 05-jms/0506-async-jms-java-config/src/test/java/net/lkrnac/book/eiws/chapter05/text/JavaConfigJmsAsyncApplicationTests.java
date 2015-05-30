package net.lkrnac.book.eiws.chapter05.text;

import net.lkrnac.book.eiws.chapter05.text.JavaConfigJmsAsyncApplication;
import net.lkrnac.book.eiws.chapter05.text.test.CommonJmsSimpleMessageTest;

import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = JavaConfigJmsAsyncApplication.class)
public class JavaConfigJmsAsyncApplicationTests extends
    CommonJmsSimpleMessageTest {
  {
    try {
      BrokerService broker = new BrokerService();
      broker.addConnector("tcp://localhost:10506");
      broker.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
