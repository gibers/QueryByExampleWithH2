package com.example.demo.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

//    @Column(nullable = true) true est la valeur par défaut.
    private String name;

    private String surname;

    private String email;

    private String address;

}
