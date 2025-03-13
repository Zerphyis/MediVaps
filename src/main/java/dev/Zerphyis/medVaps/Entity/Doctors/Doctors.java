package dev.Zerphyis.medVaps.Entity.Doctors;

import dev.Zerphyis.medVaps.Entity.Records.DataDoctors;
import dev.Zerphyis.medVaps.Entity.Specialty.Specialty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "medicos")
public class Doctors {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotBlank
    private  String name;
    @Column(unique = true)
    @NotBlank
    private String crm;
    @Column(unique = true)
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private  String phone;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Specialty doctorSpecialty;

    public Doctors(){

    }
    public Doctors(DataDoctors data){
        this.name=data.name();
        this.crm= data.crm();
        this.email= data.email();
        this.phone= data.phone();
        this.doctorSpecialty=data.doctorSpecialty();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Specialty getDoctorSpecialty() {
        return doctorSpecialty;
    }

    public void setDoctorSpecialty(Specialty doctorSpecialty) {
        this.doctorSpecialty = doctorSpecialty;
    }
}
