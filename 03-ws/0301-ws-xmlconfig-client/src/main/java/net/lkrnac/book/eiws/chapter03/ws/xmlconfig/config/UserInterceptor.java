package net.lkrnac.book.eiws.chapter03.ws.xmlconfig.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;

@Component
public class UserInterceptor implements ClientInterceptor {
  private SimpleLogger simpleLogger;

  @Autowired
  public UserInterceptor(SimpleLogger simpleLogger) {
    super();
    this.simpleLogger = simpleLogger;
  }

  @Override
  public boolean handleRequest(MessageContext messageContext)
      throws WebServiceClientException {
    simpleLogger.log("Client handleRequest");
    return true;
  }

  @Override
  public boolean handleResponse(MessageContext messageContext)
      throws WebServiceClientException {
    simpleLogger.log("Client handleResponse");
    return true;
  }

  @Override
  public boolean handleFault(MessageContext messageContext)
      throws WebServiceClientException {
    simpleLogger.log("Client handleFault");
    return true;
  }

  @Override
  public void afterCompletion(MessageContext messageContext, Exception ex)
      throws WebServiceClientException {
    simpleLogger.log("Client afterCompletion");
  }
}
