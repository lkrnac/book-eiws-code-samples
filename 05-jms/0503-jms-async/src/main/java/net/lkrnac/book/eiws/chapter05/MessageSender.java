package net.lkrnac.book.eiws.chapter05;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

import org.hornetq.api.jms.HornetQJMSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
  private JMSProducer jmsProducer;
  private Queue queue;
  private volatile int messageIndex;

  @Autowired
  public MessageSender(ConnectionFactory connectionFactory) {
    super();
    JMSContext context =
        connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE);
    jmsProducer = context.createProducer();
    queue = HornetQJMSClient.createQueue("messageQueue");
  }

  @Scheduled(fixedDelay = 1000L)
  public void send() {
    jmsProducer.send(queue, "message " + messageIndex++);
  }
}
