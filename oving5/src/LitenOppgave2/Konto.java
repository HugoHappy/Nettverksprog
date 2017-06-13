import java.rmi.*;

interface Konto extends Remote {
  long finnKontonr() throws RemoteException;
  String finnNavn() throws RemoteException;
  double finnSaldo() throws RemoteException;
  void gjørTransaksjon(double beløp) throws RemoteException;
  String lagUtskrift() throws RemoteException;
}

