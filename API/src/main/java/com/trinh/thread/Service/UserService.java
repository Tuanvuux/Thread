package com.trinh.thread.Service;
import com.trinh.thread.DTO.UserInforDTO;
import com.trinh.thread.Entity.User;
import com.trinh.thread.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Tên người dùng đã tồn tại!");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email đã được sử dụng!");
        }

        // Mã hóa mật khẩu
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public UserInforDTO findByUsername(String username){
        UserInforDTO userInforDTO = new UserInforDTO();
        User user = userRepository.findByUsername(username);
        userInforDTO.setUserId(user.getUserId());
        userInforDTO.setUsername(user.getUsername());
        userInforDTO.setEmail(user.getEmail());
        userInforDTO.setBio(user.getBio());
        userInforDTO.setRole(user.getRole());
        userInforDTO.setAvatarUrl(user.getAvatarUrl());
        userInforDTO.setCoverUrl(user.getCoverUrl());
        userInforDTO.setCreatedAt(user.getCreatedAt());
        userInforDTO.setDateOfBirth(user.getDateOfBirth());
        userInforDTO.setDisplayName(user.getDisplayName());
        userInforDTO.setPhoneNumber(user.getPhoneNumber());
        userInforDTO.setVerified(user.isVerified());
        return userInforDTO;
    }
}
