package net.lkrnac.book.eiws.chapter02.rmi.java;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public final class RmiServer {
  private static final int RMI_PORT = 10201;

  private RmiServer() {
  }

  public static void main(String[] args) throws RemoteException,
      AlreadyBoundException {
    BarServiceImpl engine = new BarServiceImpl();
    Registry registry = LocateRegistry.createRegistry(RMI_PORT);
    registry.bind("BarService", engine);
  }
}
