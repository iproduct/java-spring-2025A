package course.spring.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesDemo {
    public static void main(String[] args) throws IOException {
        var file = Paths.get("./src/course/spring/io/FilesDemo.java").toAbsolutePath();
        var outDir = Paths.get("./data").toAbsolutePath();
        if(!Files.exists(outDir)) {
            Files.createDirectories(outDir);
        }
        var out = outDir.resolve("numbered_source.txt");
        System.out.println(file);
        Charset charset = Charset.forName("utf8");
        for (String str : Charset.availableCharsets().keySet()) {
            System.out.println(str);
        }
        System.out.println("===================================================");
        try (
                BufferedReader reader = Files.newBufferedReader(file, charset);
                PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out.toFile()))));
        ) {
            String line = null;
            int n = 0;
            while ((line = reader.readLine()) != null) {
                line = ++n + ": " + line;
                writer.println(line);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        // read text file
        var lines = Files.lines(out);
        lines.forEach(System.out::println);
    }
}
