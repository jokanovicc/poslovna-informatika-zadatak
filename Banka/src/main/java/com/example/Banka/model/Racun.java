package com.example.Banka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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

    @ManyToOne
    private Banka banka;

    @ManyToOne
    private Klijent klijent;




}
