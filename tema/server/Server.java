import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import HandleClient.HandleClient;

public class Server {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server Online!");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                HandleClient handleClient = new HandleClient(clientSocket);
                handleClient.start();
                System.out.println(clientSocket.getLocalAddress() + ":" + clientSocket.getLocalPort() + " connected!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}