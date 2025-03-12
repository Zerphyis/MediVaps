package dev.Zerphyis.medVaps.Entity.Records;

import dev.Zerphyis.medVaps.Entity.Doctors.Specialty;

public record DataDoctors (String name, String crm, String email, String phone, Specialty doctorSpecialty){
}
