package dev.Zerphyis.medVaps.Entity.Notification;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "mensagens")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String mensager;
    private LocalDateTime dateTime;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensager() {
        return mensager;
    }

    public void setMensager(String mensager) {
        this.mensager = mensager;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
