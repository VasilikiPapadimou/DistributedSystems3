//Vasiliki Papadimou

package operations;

import java.rmi.*;
import java.util.ArrayList;

public interface Operations extends Remote{
    boolean addRecord(Record r) throws RemoteException;
    Record searchTitle(String title) throws RemoteException;
    ArrayList<Record> searchSinger(String singer) throws RemoteException;
    void rateTitle(Rate r) throws RemoteException;
    ArrayList<Record> getRecords(int stars) throws RemoteException;
}
