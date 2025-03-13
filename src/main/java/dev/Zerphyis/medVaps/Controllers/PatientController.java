package dev.Zerphyis.medVaps.Controllers;


import dev.Zerphyis.medVaps.Entity.Patient.Patient;
import dev.Zerphyis.medVaps.Entity.Records.DataPatient;
import dev.Zerphyis.medVaps.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PatientController {
        @Autowired
        private PatientService service;

        @GetMapping
        public List<Patient> getAllPatient() {
            return service.getAllPatient();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Patient> getPatientById(@PathVariable("id") String id) {
            return service.getPatientById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping
        public ResponseEntity<Patient> createPatient(@RequestBody DataPatient data) {
            return ResponseEntity.ok(service.createPatient(data));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Patient> updatePatient(@PathVariable("id") String id, @RequestBody DataPatient data) {
            return service.updatePatient(id, data)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletePatient(@PathVariable("id") String id) {
            return service.deletePatient(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        }
}
