package course.spring.web;

import course.spring.domain.UserService;
import course.spring.exception.InvalidEntityDataException;
import course.spring.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static course.spring.utils.ErrorHandlingUtils.handleValidationErrors;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> allUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("{id:\\d+}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("count")
    public long getUsersCount() {
        return userService.getCount();
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> addUser(@Valid @RequestBody User user, Errors errors) {
        handleValidationErrors(errors);

        var newUser = userService.addUser(user);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .pathSegment("{id}")
                        .buildAndExpand(newUser.getId()).toUri()
        ).body(newUser);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        if(!id.equals(user.getId())) {
            throw new InvalidEntityDataException(
                    String.format("Non-matching IDs in path '%s' and in request body '%s'.", id, user.getId())
            );
        }
        return userService.updateUser(user);
    }

    @DeleteMapping("{id}")
    public User deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUserById(id);
    }

}
