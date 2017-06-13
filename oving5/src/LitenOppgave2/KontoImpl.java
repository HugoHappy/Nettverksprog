import java.rmi.*;
import java.rmi.server.*;
class KontoImpl extends UnicastRemoteObject implements Konto {
  private long   kontonr;
  private String navn;
  private double saldo;

  public KontoImpl(long startKontonr, String startNavn, double startSaldo)
      throws RemoteException {
    kontonr = startKontonr;
    navn = startNavn;
    saldo = startSaldo;
  }

  public synchronized long finnKontonr() throws RemoteException {
    return kontonr;
  }

  public synchronized String finnNavn() throws RemoteException {
    return navn;
  }

  public synchronized double finnSaldo() throws RemoteException {
    return saldo;
  }

  public synchronized void gjørTransaksjon(double beløp) throws RemoteException {
    saldo = saldo + beløp;
    System.out.println("Transaksjon: " + beløp);
  }

  public synchronized String lagUtskrift() throws RemoteException {
    return "Kontonr.: " + kontonr + ", navn: " + navn + ", saldo: " + saldo;
  }
}

