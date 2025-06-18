package course.spring.io;

import course.spring.exception.CustomDBExcetion;

import java.io.*;
import java.util.Scanner;

public class ScannerDemo {
    public String readDataFromFile(String filename) throws IOException, CustomDBExcetion {
        Scanner s = null;
        StringBuilder sb = new StringBuilder();
        try (var br = new BufferedReader(new FileReader(filename))) {
            s = new Scanner(br);
            while (s.hasNext()) {
                sb.append(s.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.out.printf("Catched in readData - '%s' does not exist: %s\n", filename, ex.getMessage());
            throw new CustomDBExcetion("Error opening DB.", ex);
//            System.out.println(ex);
//            ex.printStackTrace();
        }
        return sb.toString();
    }


    public static void main(String[] args) throws IOException {
        String filename = "./src/course/spring/io/ScannerDemo2.java";
        var demo = new ScannerDemo();
        try {
            demo.readDataFromFile(filename);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (CustomDBExcetion e) {
            System.out.printf("Custom Ex. handler: %s, %s\n", e.getMessage(), e.getCause().getMessage());
        }
    }
}