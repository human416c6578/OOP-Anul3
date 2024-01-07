import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Socket clientSocket = null;
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;

        try {
            clientSocket = new Socket("localhost", 5000);
            InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            printWriter = new PrintWriter(clientSocket.getOutputStream());

            System.out.println("Connected!");

            System.out.println("Type a time in format hh:mm:ss");
            System.out.println("The server will send back the time converted in seconds.");

            while (true) {
                System.out.print("Time: ");
                String timeString = scanner.nextLine();

                if (timeString.contains("STOP"))
                    break;

                printWriter.println(timeString);
                printWriter.flush();

                String responseString = bufferedReader.readLine();
                System.out.println(responseString);
            }

            clientSocket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Disconnected!");
    }

}