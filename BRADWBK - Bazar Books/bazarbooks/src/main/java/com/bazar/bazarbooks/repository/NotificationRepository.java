package com.bazar.bazarbooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bazar.bazarbooks.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    // List<Notification> findByUserIdUser(int userId);
    List<Notification> findByUserIdUserOrderBySentDateDesc(int userId); // todas as notificações do usuário, mais recentes primeiro.
    List<Notification> findByUserIdUserAndReadFalseOrderBySentDateDesc(int userId); // somente não lidas, ordenadas por data.

}
