package com.example.Banka.web.dto;

import com.example.Banka.model.AnalitikaIzvoda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IzvodDTO {

    private Integer id;
    private Integer brojStavke;
    private String racunDuznika;
    private String racunPrimaoca;
    private Double iznos;
    private String svrhaPlacanja;
    private LocalDate vremePrenosa;


    public IzvodDTO(AnalitikaIzvoda analitikaIzvoda){
        this.id = analitikaIzvoda.getId();
        this.brojStavke = analitikaIzvoda.getBrojStavke();
        this.racunDuznika = analitikaIzvoda.getRacunDuznika();
        this.racunPrimaoca = analitikaIzvoda.getRacunPrimaoca();
        this.iznos = analitikaIzvoda.getIznos();
        this.svrhaPlacanja = analitikaIzvoda.getSvrhaPlacanja();
        this.vremePrenosa = analitikaIzvoda.getVremePrenosa();



    }


}
