import java.util.*;

/*
 * Klassen Utstyr er mutabel. Antall p� lager og nedre grense for
 * bestilling kan endres.
 */
class Utstyr {
  public static final int bestillingsfaktor = 5;
  private int nr;  // entydig identifikasjon
  private String betegnelse;
  private String leverandør;
  private int påLager;     // mengde p� lager
  private int nedreGrense;

  public Utstyr(int startNr, String startBetegnelse, String startLeverandør,
    int startPåLager, int startNedreGrense) {
    nr = startNr;
    betegnelse = startBetegnelse;
    leverandør = startLeverandør;
    påLager = startPåLager;
    nedreGrense = startNedreGrense;
  }

  public int finnNr() {
    return nr;
  }

  public String finnBetegnelse() {
    return betegnelse;
  }

  public String finnLeverandør() {
    return leverandør;
  }

  public int finnPåLager() {
    return påLager;
  }

  public int finnNedreGrense() {
    return nedreGrense;
  }

  public int finnBestKvantum() {
    if (påLager < nedreGrense) return bestillingsfaktor * nedreGrense;
    else return 0;
  }

  /*
   * Endringen kan v�re positiv eller negativ. Men det er ikke
   * mulig � ta ut mer enn det som fins p� lager. Hvis klienten
   * pr�ver p� det, vil metoden returnere false, og intet uttak gj�res.
   */
  public boolean endreLagerbeholdning(int endring) {
    System.out.println("Endrer lagerbeholdning, utstyr nr " + nr + ", endring: " + endring);
    if (påLager + endring < 0) return false;
    else {
      påLager += endring;
      return true;
    }
  }

  public void settNedreGrense(int nyNedreGrense) {
    nedreGrense = nyNedreGrense;
  }

  public String toString() {
    String resultat = "Nr: " + nr + ", " +
      "Betegnelse: " + betegnelse + ", " + "Leverandør: " +
       leverandør + ", " + "På lager: " + påLager + ", " +
      "Nedre grense: " + nedreGrense;
    return resultat;
  }
}

/*
 *
 * Et register holder orden p� en mengde Utstyrsobjekter. En klient kan legge inn nye
 * Utstyr-objekter i registeret, og ogs� endre varebeholdningen for et
 * allerede registrert objekt. Bestillingsliste for alle varene kan lages.
 */
class Register {
  public static final int ok = -1;
  public static final int ugyldigNr = -2;
  public static final int ikkeNokPåLager = -3;

  private ArrayList<Utstyr> registeret = new ArrayList<Utstyr>();

public boolean regNyttUtstyr(int startNr, String startBetegnelse,
    String startLeverandør, int startPåLager, int startNedreGrense) {
   if (finnUtstyrindeks(startNr) < 0) { // fins ikke fra f�r
     Utstyr nytt = new Utstyr(startNr, startBetegnelse, startLeverandør,
           startPåLager, startNedreGrense);
     registeret.add(nytt);
     return true;
   } else return false;
  }

  public int endreLagerbeholdning(int nr, int mengde) {
    int indeks = finnUtstyrindeks(nr);
    if (indeks < 0) return ugyldigNr;
    else {
      if (!(registeret.get(indeks)).endreLagerbeholdning(mengde)) {
        return ikkeNokPåLager;
      } else return ok;
    }
  }

  private int finnUtstyrindeks(int nr) {
    for (int i = 0; i < registeret.size(); i++) {
      int funnetNr = (registeret.get(i)).finnNr();
      if (funnetNr == nr) return i;
    }
    return -1;
  }

  public String lagBestillingsliste() {
    String resultat = "\n\nBestillingsliste:\n";
    for (int i = 0; i < registeret.size(); i++) {
      Utstyr u = registeret.get(i);
      resultat += u.finnNr() + ", " + u.finnBetegnelse() + ": " +
                  u.finnBestKvantum() + "\n";
    }
    return resultat;
  }

  public String lagDatabeskrivelse() {
    String resultat = "Alle data:\n";
    for (int i = 0; i < registeret.size(); i++) {
      resultat += (registeret.get(i)).toString() + "\n";
    }
    return resultat;
  }
}