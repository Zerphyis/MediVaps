package dev.Zerphyis.medVaps.Repositorys;

import dev.Zerphyis.medVaps.Entity.Patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository  extends JpaRepository<Patient,String> {
}
