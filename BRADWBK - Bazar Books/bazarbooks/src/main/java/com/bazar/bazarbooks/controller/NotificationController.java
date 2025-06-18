package com.bazar.bazarbooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bazar.bazarbooks.model.Notification;
import com.bazar.bazarbooks.service.NotificationService;

@RestController
@RequestMapping("notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/user/{userId}/all")
    public List<Notification> getAllNotificationsOrdered(@PathVariable int userId) {
        return notificationService.getAllNotificationsByUserOrdered(userId);
    }

    @GetMapping("/user/{userId}/unread")
    public List<Notification> getUnreadNotificationsOrdered(@PathVariable int userId) {
        return notificationService.getUnreadNotificationsByUserOrdered(userId);
    }

    @GetMapping("{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable int id) {
        Notification notification = notificationService.getNotificationById(id);
        if (notification != null) {
            return ResponseEntity.ok().body(notification);
        }
        return ResponseEntity.notFound().build();
    }

    // @PostMapping -- sem adiconar um livro como favorito
    // public ResponseEntity<Notification> putNotification(@RequestBody Notification
    // notification) {
    // Notification created = notificationService.putNotification(notification);
    // return ResponseEntity.ok(created);
    // }

    @PostMapping("/user/{userId}/book/{bookId}/favorite")
    public ResponseEntity<Notification> createFavoriteNotification(
            @PathVariable int userId,
            @PathVariable int bookId) {

        Notification created = notificationService.createNotificationForFavorite(userId, bookId);
        if (created != null) {
            return ResponseEntity.ok(created);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("{id}/read")
    public ResponseEntity<String> markAsRead(@PathVariable int id) {
        boolean updated = notificationService.markAsRead(id);
        if (updated) {
            return ResponseEntity.ok("A notificação foi marcada como lida.");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/user/{userId}/notification/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable int userId, @PathVariable int id) {
        Notification notification = notificationService.getNotificationById(id);
        if (notification != null && notification.getUser().getIdUser() == userId) {
            boolean deleted = notificationService.deleteNotification(id);
            if (deleted) {
                return ResponseEntity.ok("Notificação apagada com sucesso!");
            }
        }
        return ResponseEntity.notFound().build();
    }

}
