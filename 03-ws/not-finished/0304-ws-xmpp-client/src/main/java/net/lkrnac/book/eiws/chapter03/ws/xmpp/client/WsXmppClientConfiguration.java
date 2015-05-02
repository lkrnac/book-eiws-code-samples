package net.lkrnac.book.eiws.chapter03.ws.xmpp.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.xmpp.XmppMessageSender;
import org.springframework.ws.transport.xmpp.support.XmppConnectionFactoryBean;

@Configuration
@ComponentScan
public class WsXmppClientConfiguration {
  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath("net.lkrnac.book.eiws.chapter03.ws.xmpp.model");
    return marshaller;
  }

  @Bean
  public XmppConnectionFactoryBean connection() {
    XmppConnectionFactoryBean factoryBean = new XmppConnectionFactoryBean();
    factoryBean.setHost("jabber.blah.im");
    factoryBean.setUsername("eiws");
    factoryBean.setPassword("strong1password");
    factoryBean.setServiceName("blah.im");
    return factoryBean;
  }

  @Bean
  public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller,
      XmppConnectionFactoryBean connection) {
    WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
    webServiceTemplate.setMarshaller(marshaller);
    webServiceTemplate.setUnmarshaller(marshaller);
    webServiceTemplate.setDefaultUri("xmpp:eiws@blah.im");

    XmppMessageSender messageSender = new XmppMessageSender();
    messageSender.setConnection(connection.getObject());
    webServiceTemplate.setMessageSender(messageSender);
    return webServiceTemplate;
  }
}
