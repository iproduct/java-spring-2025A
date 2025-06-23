package course.spring.web;

import course.spring.dao.UserRepository;
import course.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersRestController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public List<User> allUsers(){
        return userRepo.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        var result = userRepo.findById(id);
        if(result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> addUser(@RequestBody User user) {
        var newUser = userRepo.create(user);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .pathSegment("{id}")
                        .buildAndExpand(newUser.getId()).toUri()
        ).body(newUser);
    }

}
