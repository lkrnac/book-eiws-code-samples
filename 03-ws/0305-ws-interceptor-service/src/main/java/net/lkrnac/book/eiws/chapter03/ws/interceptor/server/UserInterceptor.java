package net.lkrnac.book.eiws.chapter03.ws.interceptor.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;

@Component
public class UserInterceptor implements EndpointInterceptor {
  private SimpleLogger simpleLogger;

  @Autowired
  public UserInterceptor(SimpleLogger simpleLogger) {
    super();
    this.simpleLogger = simpleLogger;
  }

  @Override
  public boolean handleRequest(MessageContext messageContext, Object endpoint)
      throws Exception {
    simpleLogger.log("Endpoint handleRequest");
    return true;
  }

  @Override
  public boolean handleResponse(MessageContext messageContext, Object endpoint)
      throws Exception {
    simpleLogger.log("Endpoint handleResponse");
    return false;
  }

  @Override
  public boolean handleFault(MessageContext messageContext, Object endpoint)
      throws Exception {
    simpleLogger.log("Endpoint handleFault");
    return false;
  }

  @Override
  public void afterCompletion(MessageContext messageContext, Object endpoint,
      Exception ex) throws Exception {
    simpleLogger.log("Endpoint afterCompletion");
  }
}
