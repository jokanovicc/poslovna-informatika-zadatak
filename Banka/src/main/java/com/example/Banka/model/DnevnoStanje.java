package com.example.Banka.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@AllArgsConstructor
@NoArgsConstructor
public class DnevnoStanje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer brojIzvoda;

    private LocalDate datumIzvoda;

    private Double prethodnoStanje;

    private Double prometUKorist;

    private Double prometNaTeret;

    private Double novoStanje;

    private Double rezervisano;

    @ManyToOne
    @JsonIgnoreProperties(value = { "dnevnoStanjes", "banka", "klijent" }, allowSetters = true)
    private Racun racunPrivatnihLica;

    @OneToMany(mappedBy = "dnevnoStanje")
    private Set<AnalitikaIzvoda> analitikaIzvodas = new HashSet<>();

    @Version
    private Integer version;



}
