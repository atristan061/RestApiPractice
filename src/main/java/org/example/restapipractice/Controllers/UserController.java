package org.example.restapipractice.Controllers;

import org.example.restapipractice.Entity.User;
import org.example.restapipractice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }



//    public ResponseEntity<UserDTO> createUser(
//            @RequestBody CreateUserRequest request) {
//        return ResponseEntity.ok(userService.createUser(request));
//    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getUsers () {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User findUserById (Long id) {
        return userService.findUserById(id);
    }
}
