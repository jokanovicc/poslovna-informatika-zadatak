package com.example.Banka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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



}
