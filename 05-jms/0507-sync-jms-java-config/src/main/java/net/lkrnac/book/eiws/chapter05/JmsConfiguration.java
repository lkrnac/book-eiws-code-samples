package net.lkrnac.book.eiws.chapter05;


//@Configuration
//@EnableJms
public class JmsConfiguration {
  // @Bean
  // public ActiveMQConnectionFactory connectionFactory() {
  // ActiveMQConnectionFactory connectionFactory =
  // new ActiveMQConnectionFactory();
  // connectionFactory.setBrokerURL("tcp://localhost:61616");
  // return connectionFactory;
  // }
  //
  // @Bean
  // public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
  // return new JmsTemplate(connectionFactory);
  // }

  // @Bean
  // public DefaultMessageListenerContainer defaultMessageListenerContainer(
  // ConnectionFactory connectionFactory,
  // SimpleMessageListener simpleMessageListener) {
  // DefaultMessageListenerContainer listenerContainer =
  // new DefaultMessageListenerContainer();
  // listenerContainer.setConnectionFactory(connectionFactory);
  // listenerContainer.setDestinationName("messageQueue");
  // listenerContainer.setMessageListener(simpleMessageListener);
  // return listenerContainer;
  // }
}
