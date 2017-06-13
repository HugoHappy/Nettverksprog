import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by wizard man on 08.03.2017.
 */
public class ThreadClientHandler extends Thread {
    Socket s = null;
    InputStreamReader readConn = null;
    BufferedReader reader = null;
    PrintWriter writer = null;

    public ThreadClientHandler(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            readConn = new InputStreamReader(s.getInputStream());
            reader = new BufferedReader(readConn);
            writer = new PrintWriter(s.getOutputStream(), true);
        } catch(IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(reader.readLine());
            writer.println("Connected with server side.");

            String num1 = reader.readLine();
            String num2 = reader.readLine();
            String operation = reader.readLine();
            System.out.println("Input from client: " + num1 + " " + operation + " " + num2);

            while (!operation.equals("exit")) {
                if(operation.equals("add") && num1.matches("-?\\d+(\\.\\d+)?") && num2.matches("-?\\d+(\\.\\d+)?")) {
                    writer.println(num1 + " + " + num2 + " = " + (Integer.parseInt(num1) + Integer.parseInt(num2)));
                } else if(operation.equals("sub") && num1.matches("-?\\d+(\\.\\d+)?") && num2.matches("-?\\d+(\\.\\d+)?")) {
                    writer.println(num1 + " - " + num2 + " = " + (Integer.parseInt(num1) - Integer.parseInt(num2)));
                } else {
                    writer.println("Wrong input. Try again...");
                    System.out.println("Wrong input from client...");
                }
                num1 = reader.readLine();
                num2 = reader.readLine();
                operation = reader.readLine();
                System.out.println("Input from client: " + num1 + " " + operation + " " + num2);
            }

            reader.close();
            writer.close();
            s.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}