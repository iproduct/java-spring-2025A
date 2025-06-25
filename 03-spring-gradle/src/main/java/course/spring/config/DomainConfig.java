package course.spring.config;

import course.spring.Application;
import course.spring.dao.IdGenerator;
import course.spring.dao.UserRepository;
import course.spring.dao.impl.LongIdGenerator;
import course.spring.dao.impl.UserRepositoryInMemory;
import course.spring.domain.UserService;
import course.spring.domain.impl.UserServiceImpl;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:domain.properties")
@ComponentScan(basePackageClasses = Application.class)
public class DomainConfig {
    @Value("${idgen.initialValue}")
    private long initialValue = 0L;

    @Bean("longIdGen")
    @Scope("prototype")
    public IdGenerator idGenerator() {
        var idGen = new LongIdGenerator(); // POJO
        idGen.setInitialValue(initialValue);
        return idGen;
    }

    @Bean
    @DependsOn("longIdGen")
    public UserRepository userRepositoryInMemory(IdGenerator<Long> idGenerator) {
        return new UserRepositoryInMemory(idGenerator);
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepositoryInMemory(idGenerator()));
    }
}
