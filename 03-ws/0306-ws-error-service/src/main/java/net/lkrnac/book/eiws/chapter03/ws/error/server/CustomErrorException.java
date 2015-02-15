package net.lkrnac.book.eiws.chapter03.ws.error.server;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT)
public class CustomErrorException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public CustomErrorException(String message) {
    super(message);
  }
}
