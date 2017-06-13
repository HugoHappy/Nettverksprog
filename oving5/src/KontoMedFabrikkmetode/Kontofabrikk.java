//
// Leksjon 2, katalog KontoMedFabrikkmetode
// Kontofabrikk.java E.L. 2003-09-03
//
class Kontofabrikk {
  public static Konto lagKonto(long startKontonr, String startNavn, double startSaldo) {
    return new KontoImpl(startKontonr, startNavn, startSaldo);
  }
}
