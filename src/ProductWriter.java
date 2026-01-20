import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String ID;
        String name;
        String csvRecord;
        String description;
        double cost;
        boolean done;
        ArrayList<String> records = new ArrayList<>();

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID number");
            name = SafeInput.getNonZeroLenString(in, "Enter the name");
            description = SafeInput.getNonZeroLenString(in, "Enter the description");
            cost = SafeInput.getRangedDouble(in, "Enter the cost", 0, 1000000000);
            // create the record
            csvRecord = ID + ", " + name + ", " + description + ", " + cost;
            records.add(csvRecord);

            done = SafeInput.getYNConfirm(in, "Are you done?");
        }while (!done);

        String fileName = SafeInput.getNonZeroLenString(in, "Enter the file name");

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + File.separator + "src" + File.separator + fileName);
        // the above line of code ensures that the file is written inside the "src" file of Practicum01.

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String rec : records) {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Your data has been written into a file named '" + fileName + "'.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
