//
// Leksjon 2, katalog KontoRMI
// KontoTjener.java E.L. 2005-01-01
//
import java.rmi.Naming;
class KontoTjener {
  public static void main(String[] args) throws Exception {
    Konto olesKonto = new KontoImpl(123456676756L, "Ole Olsen", 2000);
    String objektnavn = "OleSinKonto";
    Naming.rebind(objektnavn, olesKonto);
    System.out.println("RMI-objekt er registrert");
    javax.swing.JOptionPane.showMessageDialog(null, "Trykk OK for å stoppe tjeneren.");
    Naming.unbind(objektnavn);
    System.exit(0);
  }
}
