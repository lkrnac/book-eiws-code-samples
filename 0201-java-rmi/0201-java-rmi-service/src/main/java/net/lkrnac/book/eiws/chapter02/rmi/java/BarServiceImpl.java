package net.lkrnac.book.eiws.chapter02.rmi.java;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BarServiceImpl extends UnicastRemoteObject implements BarService {
  protected BarServiceImpl() throws RemoteException {
  }

  private static final long serialVersionUID = 1L;

  @Override
  public String serveBar(String param) throws RemoteException {
    return "Bar service reponse to parameter: " + param;
  }
}
