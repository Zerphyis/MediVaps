package dev.Zerphyis.medVaps.Entity.Records;

import java.time.LocalDate;

public record DataPatient(String name, String document, String email, String phone, String addres, LocalDate birthDate) {
}
