package com.trinh.thread.Service;

import com.trinh.thread.Entity.User;
import com.trinh.thread.Jwt.JwtUtil;
import com.trinh.thread.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Tạo và trả về token nếu thông tin hợp lệ
            return jwtUtil.generateToken(username);
        }
        return null;
    }

    public boolean findAccount(String username) {
        return userRepository.existsByUsername(username);
    }
}
