package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiInterface extends Remote {

    Student getStr() throws RemoteException;

}
