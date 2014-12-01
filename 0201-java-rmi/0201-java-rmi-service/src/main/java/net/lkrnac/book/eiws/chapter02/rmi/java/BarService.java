package net.lkrnac.book.eiws.chapter02.rmi.java;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BarService extends Remote {
  String serveBar(String param) throws RemoteException;
}
