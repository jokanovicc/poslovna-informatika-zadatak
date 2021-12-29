package com.example.Banka.web;

import com.example.Banka.model.Klijent;
import com.example.Banka.service.KlijentService;
import com.example.Banka.web.dto.KlijentiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/klijenti")
public class KlijentController {


    @Autowired
    private KlijentService klijentService;



    @GetMapping
    private List<KlijentiDTO> getAll(){
        List<KlijentiDTO> klijentiDTOS = new ArrayList<>();
        for(Klijent k: klijentService.findAll()){
            klijentiDTOS.add(new KlijentiDTO(k));
        }

        return klijentiDTOS;
    }


}
