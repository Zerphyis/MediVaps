package dev.Zerphyis.medVaps.Service;

import dev.Zerphyis.medVaps.Entity.Patient.Patient;
import dev.Zerphyis.medVaps.Entity.Records.DataPatient;
import dev.Zerphyis.medVaps.Repositorys.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    PatientRepository repository;


    public List<Patient> getAllPatient() {
        return repository.findAll();
    }

    public Optional<Patient> getPatientById(String id) {
        return repository.findById(id);
    }

    public Patient createPatient(DataPatient data) {
        Patient doctor = new Patient(data);
        return repository.save(doctor);
    }

    public Optional<Patient> updatePatient(String id, DataPatient data) {
        return repository.findById(id).map(existPacient -> {
            existPacient.setName(data.name());
            existPacient.setDocument(data.document());
            existPacient.setEmail(data.email());
            existPacient.setPhone(data.phone());
            existPacient.setAddres(data.addres());
            existPacient.setBirthDate(data.birthDate());
            return repository.save(existPacient);
        });
    }

    public boolean deletePatient(String id) {
        return repository.findById(id).map(doctor -> {
            repository.delete(doctor);
            return true;
        }).orElse(false);
    }
}
