package course.spring.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesDemo {
    public static void main(String[] args) throws IOException {
        var file = Paths.get("./src/course/spring/io/FilesDemo.java").toAbsolutePath();
        var out = file.getParent().resolve("numbered_source.txt");
        System.out.println(file);
        Charset charset = Charset.forName("utf8");
        for (String str : Charset.availableCharsets().keySet()) {
            System.out.println(str);
        }
        System.out.println("===================================================");
        try (
                BufferedReader reader = Files.newBufferedReader(file, charset);
                BufferedWriter writer = Files.newBufferedWriter(out, charset);
        ) {
            String line = null;
            int n = 0;
            while ((line = reader.readLine()) != null) {
                line = ++n + ": " + line + "\n";
                writer.write(line, 0, line.length());
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        // read text file
        var lines = Files.lines(out);
        lines.forEach(System.out::println);
    }
}
