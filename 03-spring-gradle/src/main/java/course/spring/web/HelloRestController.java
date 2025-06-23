package course.spring.web;

import course.spring.dao.UserRepository;
import course.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hello")
public class HelloRestController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping({"", "{name}"})
    public String sayHello(@PathVariable(name = "name", required = false) String name) {
        return String.format("Hello %s, from Spring!", name != null ? name : "Guest");
    }


    //http://localhost:8081/api/hello/params?
    // from=01.01.2025&to=20.06.2025&doctor=Hristo%20Ivanov&patient=Ivan%20Petrov
    @GetMapping("params")
    public String sayHello(
            @RequestParam(name = "from", required = false) String from,
            @RequestParam(name = "to", required = false) String to,
            @RequestParam(name = "doctor", required = false) String doctor,
            @RequestParam(name = "patient", required = false) String patient
    ) {
        return String.format("From: %s<br>\n", from != null ? from : "not_present") +
                String.format("To: %s<br>\n", to != null ? to : "not_present") +
                String.format("Doctor: %s<br>\n", doctor != null ? doctor : "not_present") +
                String.format("Patient: %s<br>\n", patient != null ? patient : "not_present");
    }

    @GetMapping("users")
    public List<User> allUsers(){
        return userRepo.findAll();
    }

}
