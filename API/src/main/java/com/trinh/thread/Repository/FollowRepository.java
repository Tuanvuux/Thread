package com.trinh.thread.Repository;

import com.trinh.thread.Entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FollowRepository extends JpaRepository<Follow, Integer> {
}
