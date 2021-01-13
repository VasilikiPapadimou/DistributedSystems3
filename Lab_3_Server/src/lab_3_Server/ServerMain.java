//Vasiliki Papadimou

package lab_3_Server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {
    public static void main(String[] args) {
        try {
            // Δημιουργίοα στιγμιοτύπου RMI Server
            Server server=new Server();
            // Στο port 1099 (εξορισμου)
            Registry r = LocateRegistry.createRegistry(1099);
            // Στο URL SongsServer περιμένει για πελάτες
            Naming.rebind("//localhost/SongsServer", server);
            System.out.println("Server up and running....");
        } catch (Exception e) {
        System.out.println("Server not connected: " + e);
        }
    }
}
    
