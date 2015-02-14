package net.lkrnac.book.eiws.chapter03.ws.xmlconfig.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;

public class GlobalIntercetor implements EndpointInterceptor {
  private SimpleLogger simpleLogger;

  @Autowired
  public GlobalIntercetor(SimpleLogger simpleLogger) {
    super();
    this.simpleLogger = simpleLogger;
  }

  @Override
  public boolean handleRequest(MessageContext messageContext, Object endpoint)
      throws Exception {
    simpleLogger.log("handleRequest");
    return true;
  }

  @Override
  public boolean handleResponse(MessageContext messageContext, Object endpoint)
      throws Exception {
    simpleLogger.log("handleResponse");
    return false;
  }

  @Override
  public boolean handleFault(MessageContext messageContext, Object endpoint)
      throws Exception {
    simpleLogger.log("handleFault");
    return false;
  }

  @Override
  public void afterCompletion(MessageContext messageContext, Object endpoint,
      Exception ex) throws Exception {
    simpleLogger.log("afterCompletion");
  }

}
