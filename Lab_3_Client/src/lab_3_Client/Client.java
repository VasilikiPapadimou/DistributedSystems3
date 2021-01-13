//Vasiliki Papadimou

package lab_3_Client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import operations.*;

public class Client {
    // Σύνδεση με την διεπαφή του server
    Operations op;
    Client(Operations op){
        this.op = op;
        int ep;
        Scanner s = new Scanner(System.in);
        System.out.println("Καλωσήλθες στην εφαρμογή μας!!");
        while(true){
            System.out.println("1. Προσθήκη εγγραφής στην βάση");
            System.out.println("2. Αναζήτηση εγγραφής (με τίτλο) στην βάση");
            System.out.println("3. Αναζήτηση εγγραφής (με τραγουδιστή) στην βάση");
            System.out.println("4. Αξιολόγηση τραγουδιού");
            System.out.println("5. Εμφάνιση τραγουδιών με αστέρια");
            try{
                ep = Integer.parseInt(s.nextLine());
                if (ep==1){
                    Record r = new Record();
                    System.out.println("Τιτλος τραγουδιού:");
                    r.setTitle(s.nextLine());
                    System.out.println("Είδος τραγουδιού:");
                    r.setEidos(s.nextLine());
                    System.out.println("Βασικός τραγουδιστής:");
                    r.setSinger(s.nextLine());
                    System.out.println("Διάρκεια:");
                    r.setTimesec(Integer.parseInt(s.nextLine()));
                    try {
                        op.addRecord(r);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (ep==2){
                    System.out.println("Τιτλος τραγουδιού:");
                    try {
                        
                        Record r = op.searchTitle(s.nextLine());
                        if (r==null)
                            System.out.println("Το τραγούδι που αναζήτησες δεν βρέθηκε");
                        else
                            System.out.println("Το τραγούδι που αναζήτησες:\n"+r);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (ep==3){
                    System.out.println("Βασικός τραγουδιστής:");
                    try {
                        
                        ArrayList<Record> rs = op.searchSinger(s.nextLine());
                        if (rs.isEmpty())
                            System.out.println("Δεν βρέθηκε τραγούδι από τον τραγουδιστή που αναζήτησες");
                        else{
                            System.out.println("Τραγούδια του τραγουδιστή που αναζήτησες:");
                            // Για κάθε εγγραφή στη λίστα με τα τραγούδια που βρέθηκαν
                            for(Record r:rs){
                                System.out.println(r);
                            }
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (ep==4){
                    Rate r = new Rate();
                    System.out.println("Χρήστης:");
                    r.setUser(s.nextLine());
                    System.out.println("Τίτλος:");
                    r.setTitle(s.nextLine());
                    System.out.println("Αστέρια(1-10):");
                    int stars = Integer.parseInt(s.nextLine());
                    while(stars<0 || stars>10){
                        System.out.println("Λάθος είσοδος, πρέπει από 1 μέχρι 10:");
                        stars = Integer.parseInt(s.nextLine());
                    }
                    r.setStars(stars);
                    try {
                        op.rateTitle(r);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (ep==5){
                    System.out.println("Αστέρια(1-10):");
                    int stars = Integer.parseInt(s.nextLine());
                    while(stars<0 || stars>10){
                        System.out.println("Λάθος είσοδος, πρέπει από 1 μέχρι 10:");
                        stars = Integer.parseInt(s.nextLine());
                    }
                    ArrayList<Record> rs;
                    try {
                        rs = op.getRecords(stars);
                        if (rs.isEmpty())
                            System.out.println("Δεν βρέθηκε τραγούδι Με τόσο μεγάλη αξιολόγηση");
                        else{
                            System.out.println("Τραγούδια με μεγάλη αξιολόγηση:");
                            // Για κάθε εγγραφή στη λίστα με τα τραγούδια που βρέθηκαν
                            for(Record r:rs){
                                System.out.println(r);
                            }
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
                }
                else
                    System.exit(0);
            }catch(NumberFormatException ex){
                System.out.println("Δώσε αριθμό για επιλογή");
            }
                    
        }
        
    }
    
}
