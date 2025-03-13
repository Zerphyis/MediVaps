package dev.Zerphyis.medVaps.Service;

import com.opencsv.CSVWriter;
import dev.Zerphyis.medVaps.Entity.Appointment.Appointment;
import dev.Zerphyis.medVaps.Entity.Doctors.Doctors;
import dev.Zerphyis.medVaps.Entity.Notification.Notification;
import dev.Zerphyis.medVaps.Entity.Patient.Patient;
import dev.Zerphyis.medVaps.Entity.Records.DataAppointmentEntry;
import dev.Zerphyis.medVaps.Entity.Records.DataAppointmentExit;
import dev.Zerphyis.medVaps.Repositorys.AppointmentRepository;
import dev.Zerphyis.medVaps.Repositorys.DoctorsRepository;
import dev.Zerphyis.medVaps.Repositorys.NotificationRepository;
import dev.Zerphyis.medVaps.Repositorys.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
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
    @Autowired
    private NotificationRepository repositoryNotification;
    @Autowired
    private JavaEmailService emailService;

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

        String mensagem = "Consulta agendada com sucesso para " + data.date() + " às " + data.time();
        saveNotification(patient.get().getEmail(), mensagem);
        emailService.enviarEmailTexto(patient.get().getEmail(), "Confirmação de Consulta", mensagem);

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
        if (appointment.isEmpty()) {
            throw new RuntimeException("Consulta não encontrada.");
        }

        Appointment app = appointment.get();
        return new DataAppointmentExit(
                app.getDoctor().getName(),
                app.getPatient().getName(),
                app.getSpecialty(),
                app.getDate(),
                app.getTime(),
                app.getStatus()
        );
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

        String mensagem = "Sua consulta foi atualizada para " + data.date() + " às " + data.time();
        saveNotification(patient.get().getEmail(), mensagem);
        emailService.enviarEmailTexto(patient.get().getEmail(), "Atualização de Consulta", mensagem);

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
        Optional<Appointment> optionalAppointment = repositoryAppointment.findById(id);
        if (optionalAppointment.isEmpty()) {
            throw new RuntimeException("Consulta não encontrada.");
        }

        Appointment appointment = optionalAppointment.get();
        repositoryAppointment.deleteById(id);

        String mensagem = "Sua consulta em " + appointment.getDate() + " às " + appointment.getTime() + " foi cancelada.";
        saveNotification(appointment.getPatient().getEmail(), mensagem);
        emailService.enviarEmailTexto(appointment.getPatient().getEmail(), "Cancelamento de Consulta", mensagem);
    }

    private void saveNotification(String email, String mensagem) {
        Notification notification = new Notification();
        notification.setEmail(email);
        notification.setMensager(mensagem);
        notification.setDateTime(LocalDateTime.now());
        repositoryNotification.save(notification);
    }

    public void exportAppointmentsToCSV(int year, int month) {
        List<Appointment> appointments = listByMonth(year, month);

        String fileName = "Agendamentos_" + year + "_" + month + ".csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {

            String[] header = { "ID", "Data", "Médico", "Paciente", "Especialidade", "Status" };
            writer.writeNext(header);

            for (Appointment appointment : appointments) {
                String[] data = {
                        String.valueOf(appointment.getId()),
                        appointment.getDate().toString(),
                        appointment.getDoctor().getName(),
                        appointment.getPatient().getName(),
                        String.valueOf(appointment.getSpecialty()),
                        String.valueOf(appointment.getStatus())
                };
                writer.writeNext(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Appointment> listByMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        return repositoryAppointment.findByDateBetween(startOfMonth, endOfMonth);
    }
}

