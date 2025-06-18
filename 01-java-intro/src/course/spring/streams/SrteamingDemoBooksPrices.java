package course.spring.streams;

import course.spring.model.Book;

import java.util.Arrays;
import java.util.stream.Stream;

import static course.spring.model.MockBooks.MOCK_BOOKS;

public class SrteamingDemoBooksPrices {
    public static void main(String[] args) {
        Stream<Book> books = Arrays.stream(MOCK_BOOKS);
//        MOCK_BOOKS[0].getTitle().contains("java");
        var totalJavaPrice = books
                .filter(book -> book.getTitle().toLowerCase().contains("java"))
                .map(book -> book.getPrice())
                .reduce(0D, (acc, price) -> acc + price);
//                .forEach(title -> System.out.println(title));
        System.out.printf("Total Java books price: %8.2f\n", totalJavaPrice);
    }
}
