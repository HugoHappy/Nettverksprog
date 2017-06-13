/**
 * Created by EliasBrattli on 20/03/2017.
 */
import javax.swing.*;
import java.rmi.*;
import java.rmi.Naming;

public class Client {
    public static void main(String[] args) throws Exception{
        Register register = (Register)Naming.lookup("//localhost/RegisterImpl");
        String input = JOptionPane.showInputDialog("For å endre en vare, oppgi id");
        while (!input.equals("")) {
            int id = Integer.parseInt(input); //ingen
            input = JOptionPane.showInputDialog("Oppgi antall: ");
            int amt = Integer.parseInt(input);
            register.changeInventory(id,amt);
            System.out.println(register.makeDataDescription());
            input = JOptionPane.showInputDialog("Oppgi id, blank for å avslutt");
        }
        System.exit(0);
    }
}
