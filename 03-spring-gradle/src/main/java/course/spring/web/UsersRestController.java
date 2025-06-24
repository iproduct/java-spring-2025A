package course.spring.web;

import course.spring.dao.UserRepository;
import course.spring.domain.UserService;
import course.spring.dto.ErrorResponse;
import course.spring.exception.NonexistingEntityException;
import course.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersRestController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> allUsers(){
        return userService.getAllUsers();
    }

//    @GetMapping("{id}")
//    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
//        var result = userRepo.findById(id);
//        if(result.isPresent()) {
//            return ResponseEntity.ok(result.get());
//        }
//        return ResponseEntity.notFound().build();
//    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> addUser(@RequestBody User user) {
        var newUser = userService.addUser(user);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .pathSegment("{id}")
                        .buildAndExpand(newUser.getId()).toUri()
        ).body(newUser);
    }

    @ExceptionHandler(NonexistingEntityException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NonexistingEntityException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(404, exception.getMessage(), null)
        );
    }

}
