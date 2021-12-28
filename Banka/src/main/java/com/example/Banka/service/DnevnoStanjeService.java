package com.example.Banka.service;

import com.example.Banka.model.DnevnoStanje;
import com.example.Banka.repository.DnevnoStanjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DnevnoStanjeService {

    @Autowired
    private DnevnoStanjeRepository dnevnoStanjeRepository;


    public DnevnoStanje save(DnevnoStanje dnevnoStanje){
        return dnevnoStanjeRepository.save(dnevnoStanje);
    }

    public DnevnoStanje findByRacun(String racun, LocalDate date){
        return dnevnoStanjeRepository.findByRacunPrivatnihLicaBrojRacunaAndAndDatumIzvoda(racun,date);
    }
}
