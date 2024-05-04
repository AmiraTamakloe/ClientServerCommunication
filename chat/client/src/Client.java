import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Objects;

public class Client {
    public void start() {
        InputsHandler input = new InputsHandler();
        String serverAddress = "";
        int port = 0;

        try {
            serverAddress = input.checkIp();
            port = input.checkPort();
        } catch (IOException e) {
            System.out.println(e);
        }

        try (
                // Création d'une nouvelle connexion aves le serveur
                Socket socket = new Socket(serverAddress, port);
                // Canal sortant pour envoyer des messages au serveur
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ) {
            System.out.println("Serveur lancé sur [ " + serverAddress + " : " + port + " ]");

            MessageReceiver messageReceiver = new MessageReceiver(socket.getInputStream());

            messageReceiver.start();

            String userMessage;
            while (!Objects.equals(userMessage = stdIn.readLine(), "exit")) {
                if (userMessage.length() > 0) {
                    out.writeUTF(userMessage);
                }
            }

            out.writeUTF(userMessage);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
