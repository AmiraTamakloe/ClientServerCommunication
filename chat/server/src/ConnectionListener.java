import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class ConnectionListener extends Thread {
    public void run() {
        InputsHandler inputsHandler = new InputsHandler();

        String serverAddress;
        int serverPort;

        try {
            serverAddress = inputsHandler.checkIp();
            serverPort = inputsHandler.checkPort();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Création de la connexien pour communiquer ave les, clients
        try (
                ServerSocket Listener = new ServerSocket();
        ) {
            Listener.setReuseAddress(true);
            InetAddress serverIP = InetAddress.getByName(serverAddress);
            // Association de l'adresse et du port à la connexien
            Listener.bind(new InetSocketAddress(serverIP, serverPort));

            System.out.format("The server is running on %s:%d%n", serverAddress, serverPort);

            while (true) {
                // Important : la fonction accept() est bloquante: attend qu'un prochain client se connecte
                // Une nouvetle connection : on incémente le compteur clientNumber
                ClientHandler client = new ClientHandler(Listener.accept());
                client.start();
            }
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
        }
    }
}