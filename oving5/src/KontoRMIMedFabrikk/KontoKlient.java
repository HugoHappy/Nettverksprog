//
// Leksjon 2, katalog KontoRMIMedFabrikk
// KontoKlient.java E.L. 2005-01-18
//
import java.rmi.*;
class KontoKlient {
  public static void main(String[] args) throws Exception {
    Kontofabrikk fabrikk = (Kontofabrikk) Naming.lookup("rmi://localhost/Kontofabrikken");
    Konto k1 = fabrikk.lagKonto(12345678L, "Pernille Hansen", 5000);
    Konto k2 = fabrikk.lagKonto(33345678L, "Åshild Jensen", 6000);

    k1.gjørTransaksjon(600);
    k2.gjørTransaksjon(1000);

    System.out.println("Kontonr: " + k1.finnKontonr() + ", navn: " +
                        k1.finnNavn() + ", saldo: " + k1.finnSaldo());
    System.out.println("Kontonr: " + k2.finnKontonr() + ", navn: " +
                        k2.finnNavn() + ", saldo: " + k2.finnSaldo());
  }
}