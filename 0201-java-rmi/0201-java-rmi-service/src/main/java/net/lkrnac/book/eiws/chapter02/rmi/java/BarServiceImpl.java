package net.lkrnac.book.eiws.chapter02.rmi.java;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BarServiceImpl extends UnicastRemoteObject implements BarService {
  private static final long serialVersionUID = 1L;

  protected BarServiceImpl() throws RemoteException {
  }

  @Override
  public String serveBar(String param) throws RemoteException {
    return "Bar service response to parameter: " + param;
  }
}
