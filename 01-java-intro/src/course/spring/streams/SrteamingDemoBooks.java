package course.spring.streams;

import course.spring.model.Book;

import java.util.Arrays;
import java.util.stream.Stream;

import static course.spring.model.MockBooks.*;
public class SrteamingDemoBooks {
    public static void main(String[] args) {
        Stream<Book> books = Arrays.stream(MOCK_BOOKS);
//        MOCK_BOOKS[0].getTitle().contains("java");
        books
                .map(b -> b.getTitle().toLowerCase())
                .filter(title -> title.contains("java"))
                .flatMap(title -> Arrays.stream(title.split("\\W+")))
                .distinct()
                .sorted()
//                .filter(b -> b.getTitle().toLowerCase().contains("java"))
                .forEach(title -> System.out.println(title));
    }
}
