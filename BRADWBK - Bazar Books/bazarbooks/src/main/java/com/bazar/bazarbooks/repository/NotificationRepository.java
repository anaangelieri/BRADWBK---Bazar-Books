package com.bazar.bazarbooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bazar.bazarbooks.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByUserIdUserOrderBySentDateDesc(int userId);
    List<Notification> findByUserIdUserAndIsReadFalseOrderBySentDateDesc(int userId);

}
