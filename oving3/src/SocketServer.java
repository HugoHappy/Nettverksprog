import java.io.*;
import java.net.*;

class SocketServer {
    static final int PORT = 1250;

    public static void main(String[] args) {
        System.out.println("Log for server side. Waiting for clients to connect...");
        ServerSocket ss = null;
        Socket s = null;

        try {
            ss = new ServerSocket(PORT);
        } catch(IOException e) {
            e.printStackTrace();
        }

        while(true) {
            try {
                s = ss.accept();
            } catch(IOException e){
                e.printStackTrace();
            }
            new ThreadClientHandler(s).start();
        }
    }
}