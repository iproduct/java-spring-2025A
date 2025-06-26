package course.spring.domain.impl;

import course.spring.dao.UserRepository;
import course.spring.domain.UserService;
import course.spring.exception.NonexistingEntityException;
import course.spring.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.ServletContext;
import lombok.extern.java.Log;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import java.util.List;

@Service("userService")
@Log
public class UserServiceImpl implements UserService, BeanNameAware, ApplicationContextAware, ServletContextAware  {
    private String beanName;
    private ApplicationContext ctx;
    private ServletContext servletContext;
    private UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public static UserService createUserService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Override
    public void setBeanName(String name) {
        beanName = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @PostConstruct
    public void init() {
        log.info(String.format("!!!!! Bean '%s' constructed successfully.", beanName));
//        userRepo = ctx.getBean(UserRepository.class);
    }

    @PreDestroy
    public void cleanup() {
        log.info("!!!!!  UserService to be disposed.");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(
                () -> new NonexistingEntityException(
                        String.format("User with ID='%d' not found.", id)
                ));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(
                () -> new NonexistingEntityException(
                        String.format("User with username '%s' not found.", username)
                ));
    }

    @Override
    public User addUser(User user) {
        user.setId(null);
        return userRepo.save(user);
    }

    @Override
    public User updateUser(User user) {
        getUserById(user.getId());
        return userRepo.save(user);
    }

    @Override
    public User deleteUserById(Long id) {
        var oldUser = getUserById(id);
        userRepo.deleteById(id);
        return oldUser;
    }

    @Override
    public long getCount() {
        return userRepo.count();
    }

}
