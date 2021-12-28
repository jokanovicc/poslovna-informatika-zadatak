package com.example.Banka.web;

import com.example.Banka.model.Racun;
import com.example.Banka.service.RacunService;
import com.example.Banka.web.dto.RacuniKlijenataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/racuni")
@CrossOrigin("*")
public class RacuniController {


    @Autowired
    private RacunService racunService;


    @GetMapping("/racuni-klijenata")
    private List<RacuniKlijenataDTO> getAllRacuni(){
        List<RacuniKlijenataDTO> racuniKlijenataDTOList = new ArrayList<>();
        List<Racun> racuni = racunService.findAll();


        for(Racun r : racuni){
            racuniKlijenataDTOList.add(new RacuniKlijenataDTO(r));
        }

        return racuniKlijenataDTOList;
    }




}
