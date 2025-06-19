package course.spring.io;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Nio2Demo01 {
    public static void main(String[] args) {
        var fs = FileSystems.getDefault();

        // get fileStores
        var fileStores = fs.getFileStores();
        fileStores.forEach(System.out::println);

        // get Path
        var p1 = fs.getPath("D:/CourseSpring/git/java-spring-2025A/01-java-intro/src/course/spring/io",
                "Nio2Demo01.java");
//        System.out.println(p1);

        // check if path exists
        System.out.printf("File '%s' exists: %b\n", p1, Files.exists(p1));

        // path oerations
        var p2 = Paths.get("./src/course/spring");
        var p3 = Paths.get("./model/User.java");
        var p4 = p2.resolve(p3).normalize().toAbsolutePath();
        System.out.println(p4);
        var p5 =p1.relativize(p4);
        System.out.println(p5);
    }
}
