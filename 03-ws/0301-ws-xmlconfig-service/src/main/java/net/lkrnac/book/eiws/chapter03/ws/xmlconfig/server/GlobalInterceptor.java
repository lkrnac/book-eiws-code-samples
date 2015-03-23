package net.lkrnac.book.eiws.chapter03.ws.xmlconfig.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;

public class GlobalInterceptor implements EndpointInterceptor {
  private SimpleLogger simpleLogger;

  @Autowired
  public GlobalInterceptor(SimpleLogger simpleLogger) {
    super();
    this.simpleLogger = simpleLogger;
  }

  @Override
  public boolean handleRequest(MessageContext messageContext, Object endpoint)
      throws Exception {
    simpleLogger.log("Global handleRequest");
    return true;
  }

  @Override
  public boolean handleResponse(MessageContext messageContext, Object endpoint)
      throws Exception {
    simpleLogger.log("Global handleResponse");
    return true;
  }

  @Override
  public boolean handleFault(MessageContext messageContext, Object endpoint)
      throws Exception {
    simpleLogger.log("Global handleFault");
    return true;
  }

  @Override
  public void afterCompletion(MessageContext messageContext, Object endpoint,
      Exception ex) throws Exception {
    simpleLogger.log("Global afterCompletion");
  }

}
