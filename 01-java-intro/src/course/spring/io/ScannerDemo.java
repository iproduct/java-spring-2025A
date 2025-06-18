package course.spring.io;

import java.io.*;
import java.util.Scanner;

public class ScannerDemo  {
    public static void main(String[] args) throws IOException {

        Scanner s = null;

        try (var br = new BufferedReader(new FileReader("./src/course/spring/io/ScannerDemo.java"))){
            s = new Scanner(br);
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
        }
//        finally {
//            if (s != null) {
//                s.close();
//            }
//        }
    }
}