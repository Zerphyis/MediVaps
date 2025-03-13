package dev.Zerphyis.medVaps.Service;

import dev.Zerphyis.medVaps.Entity.Appointment.Appointment;
import dev.Zerphyis.medVaps.Entity.Doctors.Doctors;
import dev.Zerphyis.medVaps.Entity.Patient.Patient;
import dev.Zerphyis.medVaps.Entity.Records.DataAppointmentEntry;
import dev.Zerphyis.medVaps.Entity.Records.DataAppointmentExit;
import dev.Zerphyis.medVaps.Repositorys.AppointmentRepository;
import dev.Zerphyis.medVaps.Repositorys.DoctorsRepository;
import dev.Zerphyis.medVaps.Repositorys.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository repositoryAppointment;
    @Autowired
    private DoctorsRepository repositoryDoctors;
    @Autowired
    private PatientRepository repositoryPatient;

    public DataAppointmentExit scheduleAppointment(DataAppointmentEntry data) {
        Optional<Doctors> doctor = repositoryDoctors.findById(data.doctorId());
        Optional<Patient> patient = repositoryPatient.findById(data.pacientId());

        if (doctor.isEmpty() || patient.isEmpty()) {
            throw new RuntimeException("Médico ou paciente não encontrado.");
        }

        if (!doctor.get().getDoctorSpecialty().equals(data.specialty())) {
            throw new RuntimeException("O médico não possui a especialidade informada.");
        }

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor.get());
        appointment.setPatient(patient.get());
        appointment.setSpecialty(data.specialty());
        appointment.setDate(data.date());
        appointment.setTime(data.time());
        appointment.setStatus(data.status());

        repositoryAppointment.save(appointment);

        return new DataAppointmentExit(
                doctor.get().getName(),
                patient.get().getName(),
                data.specialty(),
                data.date(),
                data.time(),
                data.status()
        );
    }



    public List<DataAppointmentExit> listAppointments() {
        return repositoryAppointment.findAll().stream()
                .map(app -> new DataAppointmentExit(
                        app.getDoctor().getName(),
                        app.getPatient().getName(),
                        app.getSpecialty(),
                        app.getDate(),
                        app.getTime(),
                        app.getStatus()
                ))
                .collect(Collectors.toList());
    }

    public DataAppointmentExit findAppointmentById(Long id) {
        Optional<Appointment> appointment = repositoryAppointment.findById(id);
        if (appointment.isPresent()) {
            Appointment app = appointment.get();
            return new DataAppointmentExit(
                    app.getDoctor().getName(),
                    app.getPatient().getName(),
                    app.getSpecialty(),
                    app.getDate(),
                    app.getTime(),
                    app.getStatus()
            );
        } else {
            throw new RuntimeException("Consulta não encontrada.");
        }
    }

    public DataAppointmentExit updateAppointment(Long id, DataAppointmentEntry data) {
        Optional<Appointment> optionalAppointment = repositoryAppointment.findById(id);
        Optional<Doctors> doctor = repositoryDoctors.findById(data.doctorId());
        Optional<Patient> patient = repositoryPatient.findById(data.pacientId());

        if (optionalAppointment.isEmpty()) {
            throw new RuntimeException("Consulta não encontrada.");
        }

        if (doctor.isEmpty() || patient.isEmpty()) {
            throw new RuntimeException("Médico ou paciente não encontrado.");
        }

        if (!doctor.get().getDoctorSpecialty().equals(data.specialty())) {
            throw new RuntimeException("O médico não possui a especialidade informada.");
        }

        Appointment appointment = optionalAppointment.get();
        appointment.setDoctor(doctor.get());
        appointment.setPatient(patient.get());
        appointment.setSpecialty(data.specialty());
        appointment.setDate(data.date());
        appointment.setTime(data.time());
        appointment.setStatus(data.status());

        repositoryAppointment.save(appointment);

        return new DataAppointmentExit(
                doctor.get().getName(),
                patient.get().getName(),
                data.specialty(),
                data.date(),
                data.time(),
                data.status()
        );
    }

    public void deleteAppointment(Long id) {
        if (!repositoryAppointment.existsById(id)) {
            throw new RuntimeException("Consulta não encontrada.");
        }
        repositoryAppointment.deleteById(id);
    }
}

