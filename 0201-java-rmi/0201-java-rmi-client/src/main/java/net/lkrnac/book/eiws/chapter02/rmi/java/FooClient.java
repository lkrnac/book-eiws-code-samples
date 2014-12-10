package net.lkrnac.book.eiws.chapter02.rmi.java;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FooClient {
  public static void main(String[] args) throws RemoteException,
      NotBoundException {
    System.out.println(new FooClient().callService("Main method"));
  }

  public String callService(String parameter) {
    Registry registry;
    try {
      registry = LocateRegistry.getRegistry(5000);
      BarService barService = (BarService) registry.lookup("BarService");
      return barService.serveBar(parameter);
    } catch (RemoteException | NotBoundException e) {
      throw new RuntimeException(e);
    }
  }
}
