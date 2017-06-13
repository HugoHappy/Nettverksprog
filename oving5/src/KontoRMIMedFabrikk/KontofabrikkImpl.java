//
// Leksjon 2, katalog KontoRMIMedFabrikk
// KontofabrikkImpl.java E.L. 2003-09-04
//
import java.rmi.*;
import java.rmi.server.*;
class KontofabrikkImpl extends UnicastRemoteObject implements Kontofabrikk {

  public KontofabrikkImpl() throws RemoteException {
  }

  public Konto lagKonto(long startKontonr, String startNavn,
                 double startSaldo) throws RemoteException {
    System.out.println("Oppretter ny konto for " + startNavn);
    return new KontoImpl(startKontonr, startNavn, startSaldo);
  }
}