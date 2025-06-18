package com.bazar.bazarbooks.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazar.bazarbooks.model.Book;
import com.bazar.bazarbooks.model.Notification;
import com.bazar.bazarbooks.model.User;
import com.bazar.bazarbooks.repository.BookRepository;
import com.bazar.bazarbooks.repository.NotificationRepository;
import com.bazar.bazarbooks.repository.UserRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    // Post - quando um livro é adicionado aos favoritos
    public Notification createNotificationForFavorite(int userId, int bookId) {
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);

        if (user == null || book == null) {
            return null;
        }

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setTitle("Livro favoritado");
        notification.setMessage("Você favoritou o livro: " + book.getTitle());
        notification.setSentDate(LocalDateTime.now());
        notification.setRead(false);

        return notificationRepository.save(notification);
    }

    public List<Notification> getAllNotificationsByUserOrdered(int userId) {
        return notificationRepository.findByUserIdUserOrderBySentDateDesc(userId); // busca todas
    }

    public List<Notification> getUnreadNotificationsByUserOrdered(int userId) {
        return notificationRepository.findByUserIdUserAndReadFalseOrderBySentDateDesc(userId); // Busca as que não foram lidas
    }

    public Notification getNotificationById(int id) {
        return notificationRepository.findById(id).orElse(null);
    }

    public boolean markAsRead(int id) {
        Optional<Notification> optional = notificationRepository.findById(id);
        if (optional.isPresent()) {
            Notification notification = optional.get();
            notification.setRead(true);
            notificationRepository.save(notification);
            return true;
        }
        return false;
    }

    public boolean deleteNotification(int id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
