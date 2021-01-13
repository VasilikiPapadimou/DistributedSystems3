//icsd14151 Vasiliki Papadimou
package lab_3_Operations;
import java.rmi.*;
import java.util.ArrayList;
public interface Operations extends Remote{
    public boolean addRecord(Record r) throws RemoteException;
    public Record searchTitle(String title) throws RemoteException;
    public ArrayList<Record> searchSinger(String singer) throws RemoteException;
    public void rateTitle(Rate r) throws RemoteException;
    public ArrayList<Record> getRecords(int stars) throws RemoteException;
}
