package com.trinh.thread.Controller;

import com.mysql.cj.x.protobuf.Mysqlx;
import com.trinh.thread.Entity.User;
import com.trinh.thread.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping("")
    public ResponseEntity<?> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        User user = loginService.login(username, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Tên đăng nhập hoặc mật khẩu không chính xác!");
        }
        return ResponseEntity.ok(user);
    }
}
