package dev.Zerphyis.medVaps.Service;

import dev.Zerphyis.medVaps.Entity.Doctors.Doctors;
import dev.Zerphyis.medVaps.Entity.Records.DataDoctors;
import dev.Zerphyis.medVaps.Repositorys.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorsService {
    @Autowired
    DoctorsRepository repository;


    public List<Doctors> getAllDoctors() {
          return repository.findAll();
}

public Optional<Doctors> getDoctorById(String id) {
    return repository.findById(id);
}

public Doctors createDoctor(DataDoctors data) {
    Doctors doctor = new Doctors(data);
    return repository.save(doctor);
}

public Optional<Doctors> updateDoctor(String id, DataDoctors data) {
    return repository.findById(id).map(existingDoctor -> {
        existingDoctor.setName(data.name());
        existingDoctor.setCrm(data.crm());
        existingDoctor.setEmail(data.email());
        existingDoctor.setPhone(data.phone());
        existingDoctor.setDoctorSpecialty(data.doctorSpecialty());
        return repository.save(existingDoctor);
    });
}

public boolean deleteDoctor(String id) {
    return repository.findById(id).map(doctor -> {
        repository.delete(doctor);
        return true;
    }).orElse(false);
}
}
