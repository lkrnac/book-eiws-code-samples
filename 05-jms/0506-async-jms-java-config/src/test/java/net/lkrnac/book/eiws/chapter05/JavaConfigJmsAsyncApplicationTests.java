package net.lkrnac.book.eiws.chapter05;

import net.lkrnac.book.eiws.chapter05.test.simplemessage.CommonJmsSimpleMessageTest;
import net.lkrnac.book.eiws.chapter05.test.simplemessage.CommonJmsSimpleMessageTest.TestSimpleMessageConfiguration;

import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = {
    JavaConfigJmsAsyncApplication.class, TestSimpleMessageConfiguration.class })
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
