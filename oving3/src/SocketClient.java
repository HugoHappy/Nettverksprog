import java.io.*;
import java.net.*;
import java.util.Scanner;

class SocketClient {
    public static void main(String[] args) throws IOException {
        final int PORT = 1250;

        Scanner readFromCmd = new Scanner(System.in);
        InetAddress address = InetAddress.getLocalHost();

        Socket s = new Socket("localhost", PORT);
        System.out.println("Connection established.");

        InputStreamReader readConn = new InputStreamReader(s.getInputStream());
        BufferedReader reader = new BufferedReader(readConn);
        PrintWriter writer = new PrintWriter(s.getOutputStream(), true);

        writer.println("Connection established: " + address + ":" + PORT);
        String setupInfo = reader.readLine();
        System.out.println(setupInfo);

        System.out.println("\nEnter two numbers to calculate with:");
        String num1 = readFromCmd.nextLine();
        String num2 = readFromCmd.nextLine();
        System.out.println("Write add or sub to calculate " + num1 + " +/- " + num2 + ": (or quit with exit)");
        String operation = readFromCmd.nextLine();

        while (!operation.equals("exit")) {
            writer.println(num1);
            writer.println(num2);
            writer.println(operation);

            String response = reader.readLine();
            System.out.println("Echo from server: " + response);

            System.out.println("\nEnter two numbers to calculate with:");
            num1 = readFromCmd.nextLine();
            num2 = readFromCmd.nextLine();
            System.out.println("Write add or sub to calculate " + num1 + " +/- " + num2 + ": (or quit with exit)");
            operation = readFromCmd.nextLine();
        }

        reader.close();
        writer.close();
        s.close();
    }
}