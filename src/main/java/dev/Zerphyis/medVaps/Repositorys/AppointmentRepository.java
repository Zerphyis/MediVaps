package dev.Zerphyis.medVaps.Repositorys;

import dev.Zerphyis.medVaps.Entity.Appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
