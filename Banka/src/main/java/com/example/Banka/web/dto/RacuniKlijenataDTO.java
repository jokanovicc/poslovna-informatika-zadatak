package com.example.Banka.web.dto;

import com.example.Banka.model.Racun;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RacuniKlijenataDTO {

    private Integer id;

    private String brojRacuna;


    public RacuniKlijenataDTO(Racun racun){
        this.id = racun.getId();
        this.brojRacuna = racun.getBrojRacuna();
    }


}
