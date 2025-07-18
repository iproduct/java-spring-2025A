package course.spring.model;

import java.time.format.DateTimeFormatter;
import java.util.Set;

public class MockBooks {
    public static final Book[] MOCK_BOOKS;
    static {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        MOCK_BOOKS = new Book[]{
                new Book("Thinking in Java", "Bruce Eckel", 2001,
                        "Pearson", 35.5, "Detailed introduction to Java programming.", Set.of("java", "intro")),
                new Book("Effective Java", "Joshua Bloch", 2010,
                        "Addison-Wesley Professional", 35.5, "In depth Java book.", Set.of("java", "advanced")),
                new Book("Java: The Complete Reference", "Herbert Schildt ",
                       2018, "McGraw-Hill Education", 51.85),
                new Book("Thinking in java 2nd ed.", "Bruce Eckel", 2006,
                        "Pearson", 35.5, "Detailed introduction to Java programming.",Set.of("java", "intro")),
                new Book("Effective C++ ", "M. Fowler", 2017,
                        "Addison-Wesley Professional", 35.5, "In depth C++ book.", Set.of("java", "advanced")),
                new Book("Java: The Complete Reference", "Herbert Schildt ",
                        2018, "McGraw-Hill Education", 51.85),
                new Book("Thinking in Java 3rd ed.", "Bruce Eckel",2006,
                        "Pearson", 35.5, "Detailed introduction to Java programming.", Set.of("java", "intro")),
                new Book("Effective java 3rd ed.", "Joshua Bloch", 2017,
                        "Addison-Wesley Professional", 35.5, "In depth Java book.", Set.of("java", "advanced"))
        };
    }
}
