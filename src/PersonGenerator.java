import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String ID;
        String firstName;
        String lastName;
        String title;
        String csvRecord;
        int YOB;
        boolean done;
        ArrayList<String> records = new ArrayList<>();

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID number");
            firstName = SafeInput.getNonZeroLenString(in, "Enter the first name");
            lastName = SafeInput.getNonZeroLenString(in, "Enter the last name");
            title = SafeInput.getNonZeroLenString(in, "Enter the title");
            YOB = SafeInput.getRangedInt(in, "Enter the year of birth", 0, 100000);
            // create the record
            csvRecord = ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB;
            records.add(csvRecord);

            done = SafeInput.getYNConfirm(in, "Are you done?");
        }while (!done);

        System.out.println("Enter the file name: ");
        String fileName = in.nextLine();

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + '/' + "src" + '/' + fileName);
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