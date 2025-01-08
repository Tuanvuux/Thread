package com.trinh.thread.Repository;

import com.trinh.thread.Entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {
}
