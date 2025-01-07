package com.trinh.threadversiontwo.Repository;

import com.trinh.threadversiontwo.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
