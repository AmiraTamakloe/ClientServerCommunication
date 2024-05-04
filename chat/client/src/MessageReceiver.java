import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MessageReceiver extends Thread {
    private InputStream inputStream;

    public MessageReceiver(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        try (
                // Céatien d'un canal entrant pour recevoir les messages envoyés, par le serveur
                DataInputStream in = new DataInputStream(inputStream)
        ) {
            while (true) {
                if (in.available() > 0) {
                    // Attente de la réception d'un message envoyé par le, server sur le canal
                    String message = in.readUTF();
                    System.out.println(message);
                }
            }
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
        }
    }
}
