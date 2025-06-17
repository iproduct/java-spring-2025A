import course.spring.dao.Repository;
import course.spring.dao.impl.LongIdGenerator;
import course.spring.dao.impl.RepositoryInMemory;
import course.spring.model.Person;
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
//        users.add(new Person("Jane","Doe", LocalDate.of(1982, 9, 21)));
//        Users.forEach(System.out::println);

        Repository<Long, User> userRepo = new RepositoryInMemory<>(new LongIdGenerator());

        // fill users into repository
        for(User u : users) {
            userRepo.create(u);
        }

        // get and print all users
        var allUsers = userRepo.findAll();
        allUsers.forEach(System.out::println);

    }
}