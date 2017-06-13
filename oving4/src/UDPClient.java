import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
class UDPClient {
    public static void main(String[] args) throws IOException {
        final int PORT = 1250;

        InetAddress address = InetAddress.getLocalHost();
        InputStreamReader readConn = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(readConn);
        DatagramSocket ds = new DatagramSocket();

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        String operation = "";

        while(!operation.equals("exit")) {
            System.out.println("Enter two numbers to calculate with:");

            String num1 = reader.readLine();
            sendData = num1.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, PORT);
            ds.send(sendPacket);

            String num2 = reader.readLine();
            sendData = num2.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, address, PORT);
            ds.send(sendPacket);

            System.out.println("Write add or sub to calculate " + num1 + " +/- " + num2 + ": (or close connection with exit)");

            operation = reader.readLine();
            sendData = operation.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, address, PORT);
            ds.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            ds.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER: " + modifiedSentence + "\n");
        }

        //ds.close();

    }
}