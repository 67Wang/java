package jms;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class JmsClient {

    public static void main(String[] args) throws Exception {

        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("jmxBean:name=111");
        mBeanServer.registerMBean(new ServerMonitor(), helloName);
        Thread.sleep(60 * 60 * 1000);
    }
}
