package net.lkrnac.book.eiws.chapter03.ws.objectfactory.server;

import localhost._10308._0308_ws_objectfactory_service.EmployeeType;
import localhost._10308._0308_ws_objectfactory_service.ObjectFactory;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EmployeeEndpoint {
  private final ObjectFactory objectFactory = new ObjectFactory();

  @PayloadRoot(namespace = ServerConfiguration.NAMESPACE, localPart = "UserRequest")
  @ResponsePayload
  public EmployeeType getEmployee() {
    EmployeeType employee = new EmployeeType();
    employee.setFirstName("Lubos");
    employee.setLastName("Krnac");
    employee.setPositionName("Principal Software Saboter");
    return employee;
  }
}
