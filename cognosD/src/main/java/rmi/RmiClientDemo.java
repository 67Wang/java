package rmi;

import java.rmi.Naming;

public class RmiClientDemo {

    public static void main(String[] args) throws Exception {

        RmiInterface lookup = (RmiInterface)Naming.lookup("rmi://127.0.0.1:1099/getStr");

        System.out.println(lookup.getStr().getName());

    }
}
