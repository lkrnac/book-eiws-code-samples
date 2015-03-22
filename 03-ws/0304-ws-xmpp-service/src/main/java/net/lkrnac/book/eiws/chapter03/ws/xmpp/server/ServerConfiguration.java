package net.lkrnac.book.eiws.chapter03.ws.xmpp.server;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.transport.xmpp.XmppMessageReceiver;
import org.springframework.ws.transport.xmpp.support.XmppConnectionFactoryBean;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ServerConfiguration {
  public static final String NAMESPACE = "xmpp:eiws@blah.im";

  // public static final String NAMESPACE = "xmpp:durosurina@gmail.com";

  @Bean
  public XsdSchema userDetailsSchema() {
    return new SimpleXsdSchema(new ClassPathResource("userDetails.xsd"));
  }

  private class SecureXmppConnection extends XmppConnectionFactoryBean {
    @Override
    protected ConnectionConfiguration createConnectionConfiguration(
        String host, int port, String serviceName) {
      ConnectionConfiguration config =
          super.createConnectionConfiguration(host, port, serviceName);
      config.setSASLAuthenticationEnabled(true);
      config.setSecurityMode(ConnectionConfiguration.SecurityMode.enabled);
      return config;
    }

  }

  @Bean
  public XmppConnectionFactoryBean connection() {
    // SASLAuthentication.supportSASLMechanism("PLAIN", 0);
    // SecureXmppConnection factoryBean = new SecureXmppConnection();
    XmppConnectionFactoryBean factoryBean = new XmppConnectionFactoryBean();
    factoryBean.setHost("jabber.blah.im");
    factoryBean.setUsername("eiws");
    factoryBean.setPassword("strong1password");
    factoryBean.setServiceName("blah.im");
    // factoryBean.setHost("talk.google.com");
    // factoryBean.setUsername("durosurina");
    // factoryBean.setPassword("strong1password");
    // factoryBean.setServiceName("gmail.com");
    return factoryBean;
  }

  @Bean
  public XmppMessageReceiver messagingReceiver(
      XmppConnectionFactoryBean connection) {
    XmppMessageReceiver messageReceiver = new XmppMessageReceiver();
    messageReceiver.setMessageFactory(new SaajSoapMessageFactory());
    XMPPConnection lalala = connection.getObject();
    messageReceiver.setConnection(lalala);
    // messageReceiver.setMessageReceiver(messageDispatcher);
    return messageReceiver;
  }

  @Bean
  public DefaultWsdl11Definition userDetails(XsdSchema userDetailsSchema) {
    DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();
    wsdlDefinition.setTargetNamespace(NAMESPACE);
    wsdlDefinition.setSchema(userDetailsSchema);
    wsdlDefinition.setPortTypeName("UserDetailsPort");
    wsdlDefinition.setLocationUri("/wsdl/");
    return wsdlDefinition;
  }

  @Bean
  public ServletRegistrationBean dispatcherServlet(
      ApplicationContext applicationContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean(servlet, "/*");
  }
}
