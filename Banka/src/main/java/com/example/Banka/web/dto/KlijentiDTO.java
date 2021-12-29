package com.example.Banka.web.dto;

import com.example.Banka.model.Klijent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KlijentiDTO {

    private Integer id;
    private String ime;



    public KlijentiDTO(Klijent klijent){
        this.id = klijent.getId();
        this.ime = klijent.getIme();
    }




}
