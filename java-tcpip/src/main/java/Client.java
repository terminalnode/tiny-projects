import java.io.*;
import java.net.*;
class Client {
    public static void main(String[] args) {
        String line, newLine, serverName;
        BufferedReader systemInput = new BufferedReader(new InputStreamReader(System.in));

        // Get hostname for the server from command line,
        // if empty try to connect to localhost.
        if (args.length > 0) {
            serverName = args[0];
        } else {
            serverName = "localhost";
        }

        try {
            // Communication Endpoint for client and server
            Socket cs = new Socket(serverName,6789);
            System.out.println("Client connected to server!");

            // BufferedReader to read data from input stream
            BufferedReader receivedMsg = new BufferedReader(new InputStreamReader(cs.getInputStream()));

            // DataOutputStream to write data on outut stream
            DataOutputStream outgoingMsg = new DataOutputStream(cs.getOutputStream());

            while (true) {
                // We've received a message, let's read it!
                if (receivedMsg.ready()) {
                    line = receivedMsg.readLine();
                    System.out.println("Server: " + line);
                }

                // We've composed a message, let's send it!
                if (systemInput.ready()) {
                    newLine = systemInput.readLine();

                    if (newLine.equals("q")) {
                        outgoingMsg.writeBytes("Client going down, bye! \n");
                        return;
                    } else {
                        outgoingMsg.writeBytes(newLine + '\n');
                        outgoingMsg.flush();
                    }
                }
            }
        } catch(Exception e) {
            if (e instanceof java.net.ConnectException) {
                ConnectException ce = (ConnectException) e;
                System.out.println("Oh no, a connect exception has occurred!\n" + ce.getMessage());
            } else if (e instanceof java.net.SocketException) {
                SocketException se = (SocketException) e;
                System.out.println("Oh no, a socket exception has occurred!\n" + se.getMessage());
            } else {
                System.out.println("Oh no, an unknown error has occurred! \n" +  e.toString());
            }
        }
    }
}