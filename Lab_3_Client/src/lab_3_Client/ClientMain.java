//Vasiliki Papadimou

package lab_3_Client;

import java.net.MalformedURLException;
import java.rmi.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import operations.Operations;

public class ClientMain {
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        String name = "//localhost/SongsServer";
        Operations look_op =(Operations) Naming.lookup(name);
        Client c = new Client(look_op);

    }
    
}
