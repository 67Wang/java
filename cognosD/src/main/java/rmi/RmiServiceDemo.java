package rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RmiServiceDemo {
    static {
        try {
            LocateRegistry.createRegistry(1099);
        }catch (Exception exc){
            exc.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {

        RmiInterfaceImpl remoteService = new RmiInterfaceImpl();

        Naming.rebind("rmi://127.0.0.1:1099/getStr", remoteService);
    }
}
