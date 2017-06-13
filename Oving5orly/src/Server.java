import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by EliasBrattli on 20/03/2017.
 */
public class Server {

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

        RegisterImpl register = new RegisterImpl();
        // Bind this object instance to the name "RmiServer"
        Naming.rebind("//localhost/RegisterImpl", register);
        System.out.println("PeerServer bound in registry");
        register.registerItem(1,"a","cola",100,10);
        register.registerItem(2,"b","cola",100,10);
        register.registerItem(3,"c","cola",100,10);
        register.registerItem(4,"d","cola",100,10);
        register.registerItem(5,"e","cola",100,10);
        System.out.println(register.makeDataDescription());
        System.out.println(register.makeOrderList());
    }
}
