package com.trinh.thread.Repository;

import com.trinh.thread.Entity.Repost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepostRepository extends JpaRepository<Repost, Integer> {
}
