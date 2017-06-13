import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by KristofferLaptop on 20-Mar-17.
 */
interface Register extends Remote {
    boolean registerItem(int id, String designation, String supplier, int inStorage, int lowerLim) throws RemoteException;
    int changeInventory(int id,int amount)throws RemoteException;
    String makeOrderList()throws RemoteException;
    String makeDataDescription()throws RemoteException;
}