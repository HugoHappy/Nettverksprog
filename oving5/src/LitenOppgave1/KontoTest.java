class KontoTest {
  public static void main(String[] args) {

    // sammeNavn() kun kjent i klassen
    KontoImpl olesKonto1 = new KontoImpl(123456676756L, "Ole Olsen", 2300.50);
    KontoImpl olesKonto2 = new KontoImpl(123456676756L, "Ole Olsen", 2300.50);
    KontoImpl karisKonto1 = new KontoImpl(123456676756L, "Kari Olsen", 2300.50);

    if (olesKonto1.sammeNavn(olesKonto2)) System.out.println("Oles 1 og 2 har samme navn");
    if (!olesKonto1.sammeNavn(karisKonto1)) System.out.println("Ikke samme navn");

    // prøver toString()
    System.out.println(olesKonto1 + "\n" + olesKonto2 + "\n" + karisKonto1);
  }
}
/* Kjøring av programmet:
Oles 1 og 2 har samme navn
Ikke samme navn
Kontonr.: 123456676756, navn: Ole Olsen, saldo: 2300.5
Kontonr.: 123456676756, navn: Ole Olsen, saldo: 2300.5
Kontonr.: 123456676756, navn: Kari Olsen, saldo: 2300.5

*/
