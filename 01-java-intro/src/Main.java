import course.spring.dao.Repository;
import course.spring.dao.UserRepository;
import course.spring.dao.impl.LongIdGenerator;
import course.spring.dao.impl.RepositoryInMemory;
import course.spring.dao.impl.UserRepositoryInMemory;
import course.spring.model.Person;
import course.spring.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("Ivan", "Petrov", LocalDate.of(1978, 5, 17),
                "ivan", "ivan123", "ivan@gmail.com"));
        users.add(new User("John", "Smith", LocalDate.of(1982, 7, 3),
                "sjohn", "john123", "john@gmail.com"));
        users.add(new User("Mary", "Smith", LocalDate.of(1985, 4, 21),
                "mary", "mary123", "mary@yahoo.com"));
        users.add(new User("Zornica", "Dimitrova", LocalDate.of(1987, 4, 17),
                "moni", "mary123", "mony@yahoo.com"));
        users.add(new User("Dimitar", "Dimitrov", LocalDate.of(1987, 4, 17),
                "moni", "mary123", "mony@yahoo.com"));
        users.add(new User("Monica", "Dimitrova", LocalDate.of(1987, 4, 17),
                "moni", "mary123", "mony@yahoo.com"));
        users.add(new User("Maya", "Smith", LocalDate.of(1984, 8, 8),
                "maya", "mary123", "maya@yahoo.com"));
        users.add(new User("Maya", "Hristova", LocalDate.of(1987, 7, 29),
                "maya2", "mary123", "mayah@yahoo.com"));
//        users.add(new Person("Jane","Doe", LocalDate.of(1982, 9, 21)));
//        Users.forEach(System.out::println);

        UserRepository userRepo = new UserRepositoryInMemory(new LongIdGenerator());

        // fill users into repository
        for (User u : users) {
            userRepo.create(u);
        }

        // get and print all users
        var allUsers = userRepo.findAll();
        allUsers.forEach(System.out::println);

        System.out.println(userRepo.findByUsername("john"));

        System.out.println("\nUsers sorted by name:");
//        users.sort((u1, u2) -> -u1.compareTo(u2));
//        users.sort((u1, u2) -> u1.getUsername().toUpperCase().compareTo(u2.getUsername().toUpperCase()));
//        users.sort(Comparator.comparing(u -> u.getUsername().toUpperCase()));
//        users.sort(Comparator.<User, LocalDate>comparing( (User u) -> u.getDateOfBirth()).reversed());
        Comparator<User> byBirthThenByFirst = (User u1, User u2) -> u1.getDateOfBirth().compareTo(u2.getDateOfBirth()) != 0 ?
                u1.getDateOfBirth().compareTo(u2.getDateOfBirth()) : u1.getFirstName().compareTo(u2.getFirstName());
        Comparator<User> byBirthThenByFirst2 = (User u1, User u2) -> {
            var compBDate = u1.getDateOfBirth().compareTo(u2.getDateOfBirth());
            if (compBDate != 0) {
                return compBDate;
            } else {
                return u1.getFirstName().compareTo(u2.getFirstName());
            }
        };

//        users.sort( byBirthThenByFirst.reversed() );
        Collections.sort(users);
        Collections.sort(users, Comparator.reverseOrder());
        users.forEach(System.out::println);

    }
}