package com.example.Banka.service;

import com.example.Banka.model.Klijent;
import com.example.Banka.repository.KlijentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KlijentService {


    @Autowired
    private KlijentRepository klijentRepository;


    public List<Klijent> findAll(){
        return klijentRepository.findAll();
    }

    public Klijent findOne(Integer id){
        return klijentRepository.findById(id).orElse(null);
    }
}
