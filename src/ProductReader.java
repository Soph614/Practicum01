import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // name and initialize scanner
        String fileToRead;
        String line;
        String dataOutput;
        String heading;
        boolean done = false;

        JFileChooser chooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);

        try {
            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream inputStream = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                heading = String.format("%-8s %-20s %-40s %10s", "ID#", "Name", "Description", "Cost");
                System.out.println(heading);
                String heading2 = String.format("%081d", 0).replace("0", "=");
                System.out.print(heading2 + "\n");
                while(reader.ready()) {
                    line = reader.readLine();
                    String [] dataElements;
                    String [] indexElements;
                    dataElements = line.split(", ");
                    dataOutput = String.format("%-8s %-20s %-40s %10s", dataElements);
                    System.out.print(dataOutput);
                    System.out.println();
                }
                reader.close();
            }
            else {
                System.out.println("You did not choose a file. Program terminating. Rerun to try again.");
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


