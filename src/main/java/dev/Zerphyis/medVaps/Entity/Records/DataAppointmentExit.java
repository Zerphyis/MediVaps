package dev.Zerphyis.medVaps.Entity.Records;

import dev.Zerphyis.medVaps.Entity.Appointment.Status;
import dev.Zerphyis.medVaps.Entity.Specialty.Specialty;

import java.time.LocalDate;
import java.time.LocalTime;

public record DataAppointmentExit(String nameDoctor, String namePacient, Specialty specialty, LocalDate date, LocalTime time, Status status) {
}
