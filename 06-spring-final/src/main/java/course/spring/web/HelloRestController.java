package course.spring.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import course.spring.dao.UserRepository;
import course.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hello")
public class HelloRestController {

    @Autowired
    private ObjectMapper objectMapper;

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

    @GetMapping(value= "headers", produces = "text/html")
    public String acceptHeaders(@RequestHeader("Accept") String accept) {
        return String.format("<h1>Accept: %s</h1>", accept);
    }

    @GetMapping(value= "headers", produces = "application/json")
    public String acceptHeaders(@RequestHeader MultiValueMap headers) throws JsonProcessingException {
        return objectMapper.writeValueAsString(headers.toSingleValueMap());
    }

    @GetMapping(value = {"", "{name}"}, produces = "text/html")
    public String sayHello(@PathVariable(name = "name", required = false) String name) {
        return String.format("Hello %s, from Spring!", name != null ? name : "Guest");
    }

}
