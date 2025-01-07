package com.trinh.threadversiontwo.Repository;

import com.trinh.threadversiontwo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
