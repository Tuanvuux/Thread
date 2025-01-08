package com.trinh.thread.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private Date dateOfBirth;
    private String displayName;

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }


}

