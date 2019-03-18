package rmi;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class RmiInterfaceImpl extends UnicastRemoteObject implements RmiInterface {

    protected RmiInterfaceImpl() throws RemoteException {
    }

    protected RmiInterfaceImpl(int port) throws RemoteException {
        super(port);
    }

    protected RmiInterfaceImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public Student getStr() throws RemoteException {

        Student student = new Student();
        student.setName("xxx");
        return student;
    }

}
