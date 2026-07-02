package org.example.restapipractice.Service;

import org.example.restapipractice.Entity.User;
import org.example.restapipractice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    public UserDTO createUser (CreateUserRequest request) {
//        User user = new User();
//
//        user.setUserName(request.name());
//        user.setEmailAddress(request.email());
//
//        User savedUser = userRepository.save(user);
//
//        System.out.println(savedUser.getId());
//        Long id = savedUser.getId();
//
//        return new UserDTO(id, user);
//    }


    public User createUser (User user) {

        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }
}
