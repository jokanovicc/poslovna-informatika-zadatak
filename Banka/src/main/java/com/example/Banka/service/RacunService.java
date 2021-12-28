package com.example.Banka.service;

import com.example.Banka.model.Racun;
import com.example.Banka.repository.RacunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RacunService {

    @Autowired
    private RacunRepository racunRepository;


    public Racun findByRacunString(String racunDuznika){
        return racunRepository.findByBrojRacuna(racunDuznika);
    }


    public List<Racun> findAll(){
        return racunRepository.findAll();
    }


    public Boolean poklapanjeBanki(String racunPrimaoca, String racunDuznika){
        System.out.println(racunPrimaoca + "  " + racunDuznika);

        Racun uplatilac = findByRacunString(racunDuznika);
        Racun primalac = findByRacunString(racunPrimaoca);

        if(primalac.getBanka().equals(uplatilac.getBanka())){
            return true;
        }else{
            return false;
        }

    }


}
