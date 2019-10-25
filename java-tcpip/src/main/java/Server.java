import java.io.*;
import java.net.*;

class Server {
   public static void main(String[] args) {
       String line, newLine;
       BufferedReader systemInput = new BufferedReader(new InputStreamReader(System.in));

       try {
           // Setting up server socket
           ServerSocket ss = new ServerSocket(6789);
           // Waiting for socket connection
           Socket s = ss.accept();
           System.out.println("Server Started...");

           // DataInputStream to read data from input stream
           BufferedReader receivedMsg = new BufferedReader(new InputStreamReader(s.getInputStream()));

           // DataOutputStream to write data on outut stream
           DataOutputStream outgoingMsg = new DataOutputStream(s.getOutputStream());

           System.out.println("Press 'q' if you want to exit server");
           while (true) {
               // We've received a message, print it!
               if (receivedMsg.ready()) {
                   line = receivedMsg.readLine();
                   System.out.println("Client: " + line);
               }

               // We've composed a message, send it!
               if (systemInput.ready()) {
                   newLine = systemInput.readLine();
                   if (newLine.equals("q")) {
                       outgoingMsg.writeBytes("Server going down..." + '\n');
                       return;
                   } else {
                       outgoingMsg.writeBytes(newLine + '\n');
                       outgoingMsg.flush();
                   }
               }
           }
       } catch(Exception ignored) { }
   }
}