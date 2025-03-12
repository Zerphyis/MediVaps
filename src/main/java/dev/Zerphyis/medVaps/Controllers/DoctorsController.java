package dev.Zerphyis.medVaps.Controllers;

import dev.Zerphyis.medVaps.Entity.Doctors.Doctors;
import dev.Zerphyis.medVaps.Entity.Records.DataDoctors;
import dev.Zerphyis.medVaps.Service.DoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medicos")
public class DoctorsController {
    @Autowired
    private DoctorsService service;

    @GetMapping
    public List<Doctors> getAllDoctors() {
        return service.getAllDoctors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctors> getDoctorById(@PathVariable("id") String id) {
        return service.getDoctorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Doctors> createDoctor(@RequestBody DataDoctors data) {
        return ResponseEntity.ok(service.createDoctor(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctors> updateDoctor(@PathVariable("id") String id, @RequestBody DataDoctors data) {
        return service.updateDoctor(id, data)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("id") String id) {
        return service.deleteDoctor(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
