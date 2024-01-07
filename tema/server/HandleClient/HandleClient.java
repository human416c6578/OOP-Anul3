package HandleClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HandleClient extends Thread {
    private Socket clientSocket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public HandleClient(Socket clientSocket) {
        try {
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
                if (data.length != 3) {
                    // Handle invalid input
                    String errorMessage = "Error: Time format should be hh:mm:ss!";

                    System.err.println(errorMessage);

                    printWriter.println(errorMessage);
                    printWriter.flush();

                    continue;
                }

                try {
                    int hours = Integer.parseInt(data[0]);
                    int minutes = Integer.parseInt(data[1]);
                    int seconds = Integer.parseInt(data[2]);

                    int timeInSeconds = hours * 3600 + minutes * 60 + seconds;

                    printWriter.println(Integer.toString(timeInSeconds));
                    printWriter.flush();
                } catch (NumberFormatException e) {
                    // Handle invalid input
                    String errorMessage = "Invalid numeric input: The input should contain only numeric values for hours, minutes, and seconds in the format hh:mm:ss!";

                    System.err.println(errorMessage);

                    printWriter.println(errorMessage);
                    printWriter.flush();
                }
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
