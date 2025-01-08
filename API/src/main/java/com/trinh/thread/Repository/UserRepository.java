package com.trinh.thread.Repository;

import com.trinh.thread.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
