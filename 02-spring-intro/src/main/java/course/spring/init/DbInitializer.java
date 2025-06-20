package course.spring.init;

import course.spring.dao.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Log
public class DbInitializer implements ApplicationRunner {
    @Autowired
    private UserRepository userRepo;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("User repo:" + userRepo.toString());
    }
}
