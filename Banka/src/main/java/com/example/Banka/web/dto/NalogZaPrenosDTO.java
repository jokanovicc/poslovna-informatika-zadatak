package com.example.Banka.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NalogZaPrenosDTO {

    private String uplatilac;
    private String brojRacunaUplatioca;
    private String svrhaPlacanja;
    private String brojRacunaPrimaoca;
    private Double iznos;
    private boolean hitno;

}
