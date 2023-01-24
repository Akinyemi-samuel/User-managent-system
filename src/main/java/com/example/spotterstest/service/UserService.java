package com.example.spotterstest.service;

import com.example.spotterstest.model.User;
import com.example.spotterstest.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void signinUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);
    }


    public List<User> selectalluser() {
        return userRepository.findAll();
    }

    public void deleteuserbyid(Long id) {
        this.userRepository.deleteById(id);
    }

    public User UpdateuserbyId(Long id) {
        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException("couldn`t find the user");
        }
        return user;
    }
}
