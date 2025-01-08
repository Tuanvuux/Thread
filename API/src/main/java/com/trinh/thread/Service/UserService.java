package com.trinh.thread.Service;

import com.trinh.thread.DTO.RegisterRequest;
import com.trinh.thread.Entity.User;
import com.trinh.thread.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email đã được sử dụng!");
        }
        if (userRepository.existsByUsername(request.getUserName())) {
            throw new IllegalArgumentException("Tên người dùng đã tồn tại!");
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Mã hóa mật khẩu
        user.setDateOfBirth(request.getDateOfBirth());
        user.setDisplayName(request.getDisplayName());
        user.setStatus("ACTIVE");
        user.setRole("USER");
        user.setVerified(false);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }
}

