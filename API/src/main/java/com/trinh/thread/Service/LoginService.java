package com.trinh.thread.Service;

import com.trinh.thread.DTO.Account;
import com.trinh.thread.Entity.User;
import com.trinh.thread.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean findAccount(String username) {
        boolean exist = userRepository.existsByUsername(username);
        return exist;
    }
}
