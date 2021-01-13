//icsd14151 Vasiliki Papadimou
package lab_3_Server;
import java.rmi.*;
import java.rmi.server.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import operations.*;

public class Server extends UnicastRemoteObject implements Operations{
    Statement stat;
    Connection conn;
    Server() throws RemoteException{
        dbConnect();
        dbCreateTables();
    }
    
    // Σύνδεση με τη βάση 
    private void dbConnect(){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:songs.db");
            
            stat = conn.createStatement();
            System.out.println ("Database connection established");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    // Δημιουργία πινάκων (αφού πρώτα τους διαγράψει αν υπάρχουν
    private void dbCreateTables(){
        
        try {
            stat.executeUpdate("DROP table if exists songlist;");
            stat.executeUpdate("CREATE table songlist ("
                    + "title varchar(50), "
                    + "eidos varchar(50),"
                    + "singer varchar(50),"
                    + "timesec int);");
            stat.executeUpdate("DROP table if exists ratelist;");
            stat.executeUpdate("CREATE table ratelist ("
                    + "title varchar(50), "
                    + "user varchar(50),"
                    + "stars int);");
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Προσθήκη εγγραφής
    @Override
    public boolean addRecord(Record r) throws RemoteException {
        System.out.println("addRecord!!!");
        try {
            stat.executeUpdate("INSERT INTO songlist ("
                    + "title, "
                    + "eidos, "
                    + "singer,"
                    + "timesec) "
                    + "VALUES "
                    + "('"+r.getTitle()+"',"
                    + "'"+r.getEidos()+"', " 
                    + "'"+r.getSinger()+"', " 
                    + "'"+r.getTimesec()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return false;
    }
    //Αναζήτηση με βάση τον τίτλο τραγουδιού
    @Override
    public Record searchTitle(String title) throws RemoteException {
        System.out.println("searchTitle!!!");
        ResultSet records;
        try {
            records = stat.executeQuery("SELECT * from songlist where title='"+title+"'");
            if (records.next()){
                return new Record(records.getString(1),records.getString(2),records.getString(3),records.getInt(4));
            }
            else
                return null;
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return null;
    }
    //Αναζήτηση με βάση τον τραγουδιστή 
    @Override
    public ArrayList<Record> searchSinger(String singer) throws RemoteException {
        System.out.println("searchSinger!!!");
        ArrayList<Record> recordslist = new ArrayList();
        ResultSet records;
        
        try {
            records = stat.executeQuery("SELECT * from songlist where singer='"+singer+"'");
        
            while (records.next()){
                recordslist.add(new Record(records.getString(1),records.getString(2),records.getString(3),records.getInt(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return recordslist;
    }
    //Αξιολόγηση Τραγουδιού 
    @Override
    public void rateTitle(Rate r) throws RemoteException {
        System.out.println("rateTitle!!!");
        try {
            stat.executeUpdate("INSERT INTO ratelist ("
                    + "user, "
                    + "title, "
                    + "stars) "
                    + "VALUES "
                    + "('"+r.getUser()+"',"
                    + "'"+r.getTitle()+"', " 
                    + "'"+r.getStars()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Αναζήτηση με βάση τον μέσο όρο αξιολόγησης των τραγουδιών 
    @Override
    public ArrayList<Record> getRecords(int stars) throws RemoteException{
        System.out.println("searchSinger!!!");
        ArrayList<Record> recordslist = new ArrayList();
        ResultSet records,avgrecords;
        Statement avgstat;
        try {
            // Βρίσκει κάθε ξεχωριστό τραγούδι στον πίνακα τραγουδιών
            records = stat.executeQuery("SELECT * from songlist");
            while (records.next()){
                avgstat = conn.createStatement();
                // Υπολογίζει τον μέσο όρο βαθμολογιών
                avgrecords = avgstat.executeQuery("SELECT avg(stars) from ratelist where title='"+records.getString(1)+"'");
                while(avgrecords.next()){
                    // και αν είναι μεγαλύτερος από αυτόν που θέτει ο χρήστης σαν όριο
                    // το προσθέτει στην προς επιστροφή λίστα
                    if (avgrecords.getFloat(1)>stars){
                        recordslist.add(new Record(records.getString(1),records.getString(2),records.getString(3),records.getInt(4),avgrecords.getFloat(1)));
                
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recordslist;
    } 
}