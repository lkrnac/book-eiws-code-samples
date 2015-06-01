package net.lkrnac.book.eiws.chapter06.javaasynctx;

import javax.jms.JMSContext;
import javax.jms.Queue;

import net.lkrnac.book.eiws.chapter05.text.test.TestingSimpleService;
import net.lkrnac.book.eiws.chapter06.javaasynctx.JmsConfiguration;
import net.lkrnac.book.eiws.chapter06.javaasynctx.SimpleMessageSender;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * This test is relies on separate HornetQ server. During build Maven runs it
 * via hornetq-maven-plugin.
 */
public class JavaJmsTxApplicationIT {
  private static final String MESSAGE_TEXT = "dummyMessage";

  @Test(groups = "maventests")
  public void queueTest() throws Exception {
    // GIVEN
    TestingSimpleService simpleService = new TestingSimpleService();
    try (JmsConfiguration jmsConfiguration =
        new JmsConfiguration(simpleService)) {
      jmsConfiguration.init();
      JMSContext jmsContext = jmsConfiguration.getWriteContext();
      Queue queue = jmsConfiguration.getQueue();

      // WHEN
      SimpleMessageSender messageSender =
          new SimpleMessageSender(jmsContext, queue);
      messageSender.sendMessage(MESSAGE_TEXT);
      jmsContext.commit();

      // THEN
      Assert.assertEquals(simpleService.getMessage(), MESSAGE_TEXT);
    }
  }
}
