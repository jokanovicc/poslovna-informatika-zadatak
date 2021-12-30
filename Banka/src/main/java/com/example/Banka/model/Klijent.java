package com.example.Banka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Klijent {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ime;

    private String jmbg;

    private String brojLicneKarte;

    private LocalDate datumRodjenja;

    private String adresa;

    @OneToMany(mappedBy = "klijent")
    private Set<Racun> racunPrivatnihLicas = new HashSet<>();






}
