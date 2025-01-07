package com.trinh.threadversiontwo.Repository;

import com.trinh.threadversiontwo.Entity.Repost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepostRepository extends JpaRepository<Repost, Integer> {
}
