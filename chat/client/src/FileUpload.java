import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileUpload {
    public static void main(String[] args) {
        //
        try {
            
            // cette section lit l'Adresse IP 
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter ip adress : ");
            String ip = scanner.nextLine();
    
            System.out.print("Enter file path : ");
            String filePath = scanner.nextLine();
            String[] pathPart = filePath.split("/");
            String fileName = pathPart[pathPart.length -1];
            System.out.print(fileName);
            // cette section connecte avec le server socket les deux doivent être sur le même port
            Socket socket = new Socket(ip, 5001);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
    
            Scanner sc = new Scanner(is);
            PrintWriter pw = new PrintWriter(os);
    
            String command = "READ " + filePath;
            pw.println(command);
            pw.flush();
    
            // techniquement les lignes 21 à 43 sont inutiles il faut juste pouvoir prendre le path et thats it à partir de la commande
            int sizeFile = (int) Files.size(Path.of(filePath));
            if(sizeFile == -1) {
                System.out.println("File " + filePath + " not Found ");
            } else if (sizeFile == 0) {
                System.out.println("File " + filePath + " Empty ");
            } else  {
                String FileLocation = "/Users/amiratamakloe/IdeaProjects/TD1-INF3405/server/files/" + fileName; //prend juste le nom du fichier et met le fichier dans le folder file du serveur
                FileOutputStream fos = new FileOutputStream(FileLocation);
                byte b[] = new byte[10000000]; //quand je met file size à la place d'un gros chiffre ça fonctionne pas
                int sum = 0;
                DataInputStream dis = new DataInputStream(is);
                System.out.println("on se rend la");
    
                while(true) {
                    int n = dis.read(b, 0, 10000000); //quand je met file size à la place d'un gros chiffre ça fonctionne pas
                    fos.write(b,0,n);
                    sum += n;
                    System.out.println(sum + " bytes downloaded");
                    if(sum >= sizeFile) break;
                }
                System.out.println("file downloaded succesfully");
                fos.close();
                dis.close();

            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }


    }
}
