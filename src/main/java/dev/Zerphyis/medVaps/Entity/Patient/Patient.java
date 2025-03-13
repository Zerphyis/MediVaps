package dev.Zerphyis.medVaps.Entity.Patient;

import dev.Zerphyis.medVaps.Entity.Records.DataPatient;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    @Column(unique = true)
    private String document;
    @Email
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    private String phone;
    private LocalDate birthDate;
    @NotBlank
    private String addres;

    public Patient(){

    }

    public Patient(DataPatient data){
        this.name= data.name();
        this.document= data.document();
        this.email= data.email();
        this.phone= data.phone();
        this.addres= data.addres();
        this.birthDate=data.birthDate();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }
}
