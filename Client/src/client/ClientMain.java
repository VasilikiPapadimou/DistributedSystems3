//icsd14151 Vasiliki Papadimou
package client;

import java.net.MalformedURLException;
import java.rmi.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import operations.Operations;

public class ClientMain {
    public static void main(String[] args) { 
        try {
            String name = "//localhost/SongsServer";
            Operations look_op =(Operations) Naming.lookup(name);
            Client c = new Client(look_op);
        } catch (NotBoundException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
