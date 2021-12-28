package com.example.Banka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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






}
