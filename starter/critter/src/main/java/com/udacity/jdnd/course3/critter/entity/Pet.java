package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.DTO.PetType;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private PetType type;
    private String name;

    @ManyToOne(targetEntity = Customer.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customerid")
    private Customer customer;

    private LocalDate birthDate;
    private String notes;

    //getters and setters

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", customer=" + customer +
                ", birthDate=" + birthDate +
                ", notes='" + notes + '\'' +
                '}';
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PetType getPetType() {
        return type;
    }

    public void setPetType(PetType petType) {
        this.type = petType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
