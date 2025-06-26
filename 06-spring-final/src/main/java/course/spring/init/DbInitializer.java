package course.spring.init;

import course.spring.dao.UserRepository;
import course.spring.domain.ArticleService;
import course.spring.domain.CategoryService;
import course.spring.domain.UserService;
import course.spring.model.Article;
import course.spring.model.Category;
import course.spring.model.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
@Log
public class DbInitializer implements ApplicationRunner {
    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    private static final List<User> USERS = List.of(
            new User("Ivan", "Petrov", LocalDate.of(1978, 5, 17),
                    "ivan", "ivan123", "ivan@gmail.com"),
            new User("John", "Smith", LocalDate.of(1982, 7, 3),
                    "sjohn", "john123", "john@gmail.com"),
            new User("Mary", "Smith", LocalDate.of(1985, 4, 21),
                    "mary", "mary123", "mary@yahoo.com"),
            new User("Zornica", "Dimitrova", LocalDate.of(1987, 4, 17),
                    "moni", "mary123", "mony@yahoo.com"),
            new User("Dimitar", "Dimitrov", LocalDate.of(1987, 4, 17),
                    "moni", "mary123", "mony@yahoo.com"),
            new User("Monica", "Dimitrova", LocalDate.of(1987, 4, 17),
                    "moni", "mary123", "mony@yahoo.com"),
            new User("Maya", "Smith", LocalDate.of(1984, 8, 8),
                    "maya", "mary123", "maya@yahoo.com"),
            new User("Maya", "Hristova", LocalDate.of(1987, 7, 29),
                    "maya2", "mary123", "mayah@yahoo.com")
    );

    private static final List<Category> CATEGORIES = List.of(
            new Category("Java", "Java programming"),
            new Category("Database", "Database persistence"),
            new Category("Machine Learning", "Machine learning models and libraries")
    );

    private static final List<Article> ARTICLES = List.of(
            new Article("Spring Data JPA Intro",
                    "Generally, the query creation mechanism for JPA works as described in Query Methods.",
                    Set.of("java", "spring", "data", "jpa")),
            new Article("Hibernate & JPA ORM",
                    "Hibernate, as an ORM solution, effectively \"sits between\" the Java application data access layer and the Relational Database, as can be seen in the diagram above.",
                    Set.of("java", "hibernate", "data", "jpa", "orm")),
            new Article("Spring Core Introduction",
                    "Foremost amongst these is the Spring Framework’s Inversion of Control (IoC) container.",
                    Set.of("java", "spring", "ioc", "bean", "context", "container"))
    );

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("User service:" + userService.toString());
        if(userService.getCount() == 0) {
            USERS.forEach(userService::addUser);
        }
        var users = userService.getAllUsers();
        if(categoryService.getCount() == 0) {
            CATEGORIES.forEach(categoryService::addCategory);
        }
        var categories = categoryService.getAllCategorys();
        if(articleService.getCount() == 0) {
            ARTICLES.forEach(article -> {
                article.setAuthor(users.getFirst());
                articleService.addArticle(article);
            });
        }

    }
}
