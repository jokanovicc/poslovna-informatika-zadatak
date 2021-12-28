package com.example.Banka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalitikaIzvoda {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer brojStavke;

    private Double iznos;

    private String duznik;

    private String svrhaPlacanja;

    private String vrstaPlacanja;

    private String primalac;

    private String racunDuznika;

    private String racunPrimaoca;

    private Integer model;

    private LocalDate vremePrenosa;

    private String pozivNaBroj;

    private Boolean hitno;

    @ManyToOne
    private DnevnoStanje dnevnoStanje;

}
