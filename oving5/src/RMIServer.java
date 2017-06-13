import java.util.*;
import java.rmi.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;
/*
 *
 * Et register holder orden p� en mengde Utstyrsobjekter. En klient kan legge inn nye
 * Item-objekter i register, og ogs� endre varebeholdningen for et
 * allerede registrert objekt. Bestillingsliste for alle varene kan lages.
 */
public class RMIServer extends UnicastRemoteObject implements Register {
  public static final int OK = -1;
  public static final int INVALID_ID = -2;
  public static final int EMPTY = -3;

  public RMIServer() throws RemoteException {
    super(0);    // required to avoid the 'rmic' step, see below
  }
  private ArrayList<Item> register = new ArrayList<Item>();

public boolean registerItem(int id, String designation, String supplier, int inStorage, int lowerLim) {
   if (findItem(id) < 0) { // fins ikke fra f�r
     Item item = new Item(id, designation, supplier, inStorage, lowerLim);
     register.add(item);
     return true;
   } else return false;
  }

  public int changeInventory(int id, int amount) {
    int indeks = findItem(id);
    if (indeks < 0) return INVALID_ID;
    else {
      if (!(register.get(indeks)).changeInventory(amount)) {
        return EMPTY;
      } else return OK;
    }
  }

  private int findItem(int id) {
    for (int i = 0; i < register.size(); i++) {
      if((register.get(i)).getId() == id)return i;

    }
    return -1;
  }

  public String makeOrderList() {
    String resultat = "\n\nBestillingsliste:\n";
    for (int i = 0; i < register.size(); i++) {
      Item u = register.get(i);
      resultat += u.getId() + ", " + u.getDesignation() + ": " +
                  u.findBestQuantum() + "\n";
    }
    return resultat;
  }

  public String makeDataDescription() {
    String result = "Alle data:\n";
    for (int i = 0; i < register.size(); i++) {
      result += (register.get(i)).toString() + "\n";
    }
    return result;
  }
  public static void main(String args[]) throws Exception {
    System.out.println("RMI server started");

    try { //special exception handler for registry creation
      LocateRegistry.createRegistry(1099);
      System.out.println("java RMI registry created.");
    } catch (RemoteException e) {
      //do nothing, error means registry already exists
      System.out.println("java RMI registry already exists.");
    }

    //Instantiate RmiServer

    RMIServer obj = new RMIServer();

    // Bind this object instance to the name "RmiServer"
    Naming.rebind("//localhost/RmiServer", obj);
    System.out.println("PeerServer bound in registry");
  }
}

interface Register{
    boolean registerItem(int id, String designation, String supplier, int inStorage, int lowerLim);
    int changeInventory(int id,int amount);
    String makeOrderList();
    String makeDataDescription();
}