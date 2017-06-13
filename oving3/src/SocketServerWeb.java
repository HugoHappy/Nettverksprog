import java.io.*;
import java.net.*;

class SocketServerWeb {
    static final int PORT = 80;

    public static void main(String[] args) throws Exception {
        System.out.println("Log for server side. Waiting for clients to connect...");

        ServerSocket ss = new ServerSocket(PORT);
        Socket s = ss.accept();

        InputStreamReader readConn = new InputStreamReader(s.getInputStream());
        BufferedReader reader = new BufferedReader(readConn);
        PrintWriter writer = new PrintWriter(s.getOutputStream(), true);


        writer.println("<html><body><h1>Welcome!</h1><ul>");
        String headerInfo = reader.readLine();

        while(!headerInfo.equals("")) {
            System.out.println(headerInfo);
            writer.println("<li>" + headerInfo + "</li>");
            headerInfo = reader.readLine();
        }

        writer.println("</ul></body></html>");


        reader.close();
        writer.close();
        s.close();
    }
}