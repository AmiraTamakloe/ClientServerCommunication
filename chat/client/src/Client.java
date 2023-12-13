import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.Scanner;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.IOException;

// Application client
public class Client {
    private static Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;

        } catch (IOException err) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageToSend = scanner.nextLine();
                bufferedWriter.write(username + ": " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException err) {
            // TODO: handle exception
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String mFromGC;

                while(socket.isConnected()) {
                    try {
                        mFromGC = bufferedReader.readLine();
                        System.out.println(mFromGC);

                    } catch(IOException err) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        // Adresse et port du serveur
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username for the groupchat: ");
        String username = scanner.nextLine();
        Socket socket = new Socket("localhost", 4200);
        Client client = new Client(socket, username);
        client.listenForMessage();
        client.sendMessage();
        
    }
}