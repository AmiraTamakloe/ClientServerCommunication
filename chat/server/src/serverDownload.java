import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class serverDownload {

    public static void main(String[] args) {

        try {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter file name : ");
            String fileName = scanner.nextLine();
            String sourcePath = "/Users/amiratamakloe/IdeaProjects/TD1-INF3405/server/files/" + fileName;
            System.out.print(fileName);

            int sizeFile = (int) Files.size(Path.of(sourcePath));
            if (sizeFile == -1) {
                System.out.println("File " + sourcePath + " not Found ");
            } else if (sizeFile == 0) {
                System.out.println("File " + sourcePath + " Empty ");
            } else {
                String destinationPath = "/Users/amiratamakloe/IdeaProjects/TD1-INF3405/client/files/" + fileName;
                FileInputStream fis = new FileInputStream(new File(sourcePath));
                FileOutputStream fos = new FileOutputStream(new File(destinationPath));
                byte b[] = new byte[10000000];
                int sum = 0;

                while (sum < sizeFile) {
                    int n = fis.read(b, 0, 10000000);
                    fos.write(b, 0, n);
                    sum += n;
                    System.out.println(sum + " bytes downloaded");
                }
                scanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
