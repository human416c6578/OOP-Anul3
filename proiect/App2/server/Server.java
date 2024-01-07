import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import Database.*;
import HandleClient.HandleClient;

public class Server {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            Database database = new Database("/home/mrshark45/Workspace/faculty/Anul3/POO/proiect/App2/server/books.txt");
            System.out.println("Server Online!");
            database.printEntries();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                HandleClient handleClient = new HandleClient(clientSocket, database);
                handleClient.start();
                System.out.println(clientSocket.getLocalAddress() + ":" + clientSocket.getLocalPort() + " connected!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}