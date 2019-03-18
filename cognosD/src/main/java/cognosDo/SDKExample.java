package cognosDo;

import com.cognos.developer.schemas.bibus._3.*;
import org.apache.axis.client.Stub;
import org.apache.axis.message.SOAPHeaderElement;

import javax.xml.namespace.QName;
import java.net.URL;

public class SDKExample {
    private static String dispatcherURL = "http://localhost:9300/p2pd/servlet/dispatch";
    private static String nameSpaceID = "NSID";
    private static String userName = "user";
    private static String password = "pwd";
    private ContentManagerService_PortType cmService = null;

    public static void main(String args[]) {
        SDKExample mainClass = new SDKExample(); // instantiate the class

        // Step 1: Connect to the Cognos services
        mainClass.connectToCognos(dispatcherURL);

        // Step 2: Logon to Cognos
        mainClass.logonToCognos(nameSpaceID, userName, password);

        // Step 3: Execute tasks
        mainClass.executeTasks();

        // Step 4: Logoff from Cognos
        mainClass.logoffFromCognos();
    }

    // Step 1: Connect to the Cognos services
    private void connectToCognos(String dispatcherURL) {
        ContentManagerService_ServiceLocator cmServiceLocator = new ContentManagerService_ServiceLocator();

        try {
            URL url = new URL(dispatcherURL);
            cmService = cmServiceLocator.getcontentManagerService(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Step 2: Logon to Cognos
    private void logonToCognos(String nsID, String user, String pswd) {
        StringBuffer credentialXML = new StringBuffer();

        credentialXML.append("<credential>");
        credentialXML.append("<namespace>").append(nsID).append("</namespace>");
        credentialXML.append("<username>").append(user).append("</username>");
        credentialXML.append("<password>").append(pswd).append("</password>");
        credentialXML.append("</credential>");

        String encodedCredentials = credentialXML.toString();
        XmlEncodedXML xmlCredentials = new XmlEncodedXML();
        xmlCredentials.set_value(encodedCredentials);

        try {
            cmService.logon(xmlCredentials, null);
            SOAPHeaderElement temp = ((Stub) cmService).getResponseHeader(
                    "http://developer.cognos.com/schemas/bibus/3/", "biBusHeader");
            BiBusHeader CMbibus = (BiBusHeader) temp.getValueAsType(
                    new QName("http://developer.cognos.com/schemas/bibus/3/", "biBusHeader"));
            ((Stub) cmService).setHeader("http://developer.cognos.com/schemas/bibus/3/",
                    "biBusHeader", CMbibus);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Step 3: Execute tasks
    private void executeTasks() {
        PropEnum props[] = new PropEnum[]{PropEnum.searchPath,
                PropEnum.defaultName};
        BaseClass bc[] = null;
        String searchPath = "/content//package";

        try {
            SearchPathMultipleObject spMulti = new SearchPathMultipleObject(
                    searchPath);
            bc = cmService.query(spMulti, props, new Sort[]{},
                    new QueryOptions());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("PACKAGES:\n");
        if (bc != null) {
            for (int i = 0; i < bc.length; i++) {
                System.out.println(bc[i].getDefaultName().getValue() + " - "
                        + bc[i].getSearchPath().getValue());
            }
        }
    }

    // Step 4: Logoff from Cognos
    private void logoffFromCognos() {
        try {
            cmService.logoff();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}