package com.example.Banka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Racun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brojRacuna;

    private LocalDate datumOtvaranja;

    @OneToMany(mappedBy = "racunPrivatnihLica")
    private Set<DnevnoStanje> dnevnoStanjes = new HashSet<>();

    @ManyToOne
    private Banka banka;

    @ManyToOne
    private Klijent klijent;




}
