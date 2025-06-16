import course.spring.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("Ivan","Petrov", LocalDate.of(1978, 5, 17),
                "ivan", "ivan123", "ivan@gmail.com"));
        users.add(new User("John","Smith", LocalDate.of(1982, 7, 3),
                "john", "john123", "john@gmail.com"));
        users.add(new User("Mary","Smith", LocalDate.of(1985, 4, 21),
                "mary", "mary123", "mary@yahoo.com"));
//        Users.forEach(System.out::println);
        for(User p : users) {
            System.out.println(p);
        }
        System.out.println("========================================");
        for(int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }

    }
}