package com.example.Banka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String naziv;

    private String pib;

    private String sifraBanke;

    private String adresa;

    @OneToMany(mappedBy = "banka")
    private Set<Racun> racunPrivatnihLicas = new HashSet<>();



}
