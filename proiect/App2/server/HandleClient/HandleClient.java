package HandleClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Database.*;

public class HandleClient extends Thread {
    private Database database;
    private Socket clientSocket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public HandleClient(Socket clientSocket, Database database) {
        try {
            this.database = database;
            this.clientSocket = clientSocket;
            printWriter = new PrintWriter(clientSocket.getOutputStream());
            InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
            this.bufferedReader = new BufferedReader(inputStreamReader);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            String clientInput;
            while ((clientInput = bufferedReader.readLine()) != null) {
                if (clientInput.equals("STOP")) {
                    break;
                }

                String[] data = clientInput.split(":");
                Entry entry = database.getEntry(data[0], data[1]);
                
                printWriter.println(entry.getStock().toString());
                printWriter.flush();

            }
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
        } finally {
            // Close resources if needed
            try {
                System.out.println(clientSocket.getLocalAddress() + ":" + clientSocket.getLocalPort() + " disconnected!");
                bufferedReader.close();
                printWriter.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
