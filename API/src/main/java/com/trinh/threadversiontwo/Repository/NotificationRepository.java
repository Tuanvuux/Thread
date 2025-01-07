package com.trinh.threadversiontwo.Repository;

import com.trinh.threadversiontwo.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
