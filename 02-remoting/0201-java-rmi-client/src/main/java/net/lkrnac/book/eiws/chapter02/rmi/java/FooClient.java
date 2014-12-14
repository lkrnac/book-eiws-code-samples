package net.lkrnac.book.eiws.chapter02.rmi.java;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FooClient {
  private static final int RMI_PORT = 10201;

  public static void main(String... args) throws RemoteException,
      NotBoundException {
    System.out.println(new FooClient().callService("Main method"));
  }

  @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
  public String callService(String parameter) {
    Registry registry;
    try {
      registry = LocateRegistry.getRegistry(RMI_PORT);
      BarService barService = (BarService) registry.lookup("BarService");
      return barService.serveBar(parameter);
    } catch (RemoteException | NotBoundException e) {
      throw new RuntimeException(e);
    }
  }
}
