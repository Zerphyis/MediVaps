package dev.Zerphyis.medVaps.Repositorys;

import dev.Zerphyis.medVaps.Entity.Notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}

