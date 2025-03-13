package dev.Zerphyis.medVaps.Controllers;

import dev.Zerphyis.medVaps.Entity.Records.DataAppointmentEntry;
import dev.Zerphyis.medVaps.Entity.Records.DataAppointmentExit;
import dev.Zerphyis.medVaps.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("consultas")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping()
    public ResponseEntity<DataAppointmentExit> scheduleAppointment(@RequestBody DataAppointmentEntry data) {
        try {
            DataAppointmentExit result = appointmentService.scheduleAppointment(data);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<List<DataAppointmentExit>> listAppointments() {
        List<DataAppointmentExit> appointments = appointmentService.listAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataAppointmentExit> findAppointmentById(@PathVariable("id") Long id) {
        try {
            DataAppointmentExit appointment = appointmentService.findAppointmentById(id);
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataAppointmentExit> updateAppointment(@PathVariable("id") Long id, @RequestBody DataAppointmentEntry data) {
        try {
            DataAppointmentExit updatedAppointment = appointmentService.updateAppointment(id, data);
            return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable("id") Long id) {
        try {
            appointmentService.deleteAppointment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/export")
    public ResponseEntity<String> exportAppointmentsToCSV(@RequestParam int year, @RequestParam int month) {
        try {
            appointmentService.exportAppointmentsToCSV(year, month);
            return ResponseEntity.ok("Exportação para CSV concluída.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao exportar os agendamentos.");
        }
    }
}
