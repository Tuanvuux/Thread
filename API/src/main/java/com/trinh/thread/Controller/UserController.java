package com.trinh.thread.Controller;
import com.trinh.thread.Jwt.JwtUtil;
import com.trinh.thread.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class UserController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService; // Giả sử bạn có một service để lấy thông tin người dùng

    @GetMapping("")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String authHeader) {
        // Lấy token từ header
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body("Token is missing or invalid");
        }

        String token = authHeader.substring(7); // Lấy phần token sau "Bearer "

        // Giải mã token để lấy username
        String username = jwtUtil.extractUsername(token);

        // Lấy thông tin người dùng từ username
        if (username != null) {
            return ResponseEntity.ok(userService.findByUsername(username)); // Giả sử bạn có một method trong UserService
        }

        return ResponseEntity.status(401).body("Invalid token");
    }
}
