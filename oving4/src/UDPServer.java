import java.net.*;

class UDPServer {
    static final int PORT = 1250;

    public static void main(String[] args) throws Exception {
        System.out.println("Log for server side. Waiting for clients to connect...");

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        DatagramSocket ds = new DatagramSocket(PORT);

        String operation = "";

        while(!operation.equals("exit")) {
            DatagramPacket dp = new DatagramPacket(receiveData, receiveData.length);

            ds.receive(dp);
            String num1 = new String(dp.getData(), 0, dp.getLength());
            System.out.println(num1);

            ds.receive(dp);
            String num2 = new String(dp.getData(), 0, dp.getLength());
            System.out.println(num2);

            ds.receive(dp);
            operation = new String(dp.getData(), 0, dp.getLength());
            System.out.println(operation);

            InetAddress addressPacket = dp.getAddress();
            int portPacket = dp.getPort();

            if(operation.equals("add") && num1.matches("-?\\d+(\\.\\d+)?") && num2.matches("-?\\d+(\\.\\d+)?")) {
                String add = num1 + " + " + num2 + " = " + (Integer.parseInt(num1) + Integer.parseInt(num2));
                System.out.println(add);
                sendData = add.getBytes();
                dp = new DatagramPacket(sendData, sendData.length, addressPacket, portPacket);
                ds.send(dp);
            } else if(operation.equals("sub") && num1.matches("-?\\d+(\\.\\d+)?") && num2.matches("-?\\d+(\\.\\d+)?")) {
                String sub = num1 + " - " + num2 + " = " + (Integer.parseInt(num1) - Integer.parseInt(num2));
                System.out.println(sub);
                sendData = sub.getBytes();
                dp = new DatagramPacket(sendData, sendData.length, addressPacket, portPacket);
                ds.send(dp);
            } else {
                String error = "Wrong input. Try again...";
                sendData = error.getBytes();
                dp = new DatagramPacket(sendData, sendData.length, addressPacket, portPacket);
                ds.send(dp);
                System.out.println("Wrong input from client...");
            }
        }
    }
}