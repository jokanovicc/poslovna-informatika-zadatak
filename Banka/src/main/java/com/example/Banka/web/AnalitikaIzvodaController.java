package com.example.Banka.web;

import com.example.Banka.model.AnalitikaIzvoda;
import com.example.Banka.model.DnevnoStanje;
import com.example.Banka.model.Klijent;
import com.example.Banka.model.Racun;
import com.example.Banka.service.AnalitikaIzvodaService;
import com.example.Banka.service.DnevnoStanjeService;
import com.example.Banka.service.KlijentService;
import com.example.Banka.service.RacunService;
import com.example.Banka.web.dto.IzvodDTO;
import com.example.Banka.web.dto.NalogZaPrenosDTO;
import com.example.Banka.web.dto.UpitIzvoda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/analitike-izvoda")
public class AnalitikaIzvodaController {



    @Autowired
    private AnalitikaIzvodaService analitikaIzvodaService;

    @Autowired
    private RacunService racunService;

    @Autowired
    private DnevnoStanjeService dnevnoStanjeService;

    @Autowired
    private KlijentService klijentService;




    @PostMapping(value = "nalog-za-prenos")
    public ResponseEntity<Void> nalogZaPrenos(@RequestBody NalogZaPrenosDTO nalogZaPrenosDTO){
        AnalitikaIzvoda analitikaIzvoda = new AnalitikaIzvoda();
        analitikaIzvodaService.dtoToModel(nalogZaPrenosDTO,analitikaIzvoda);
        if(racunService.poklapanjeBanki(nalogZaPrenosDTO.getBrojRacunaPrimaoca(),nalogZaPrenosDTO.getBrojRacunaUplatioca())){
            System.out.println("Nema ni RTGS ni Kliringa, novac je prenesen a ni porukica");
        }else {
            System.out.println("Različite banke - RTGS ili Kliring");
            //CASE 1 - RTGS
            if(nalogZaPrenosDTO.isHitno() || nalogZaPrenosDTO.getIznos() > 300000){
                analitikaIzvodaService.caseRTGS(analitikaIzvoda);
            }else{
                System.out.println("-------KLIRING MT103-----------");
                analitikaIzvodaService.caseClearing(analitikaIzvoda);
            }
        }
        AnalitikaIzvoda analitikaIzvodaPrimaoca = analitikaIzvodaService.makeAnalitikaIzvodaPrimaoca(analitikaIzvoda);
        //DNEVNO STANJE UPLATIOCA
        DnevnoStanje dnevnoStanje =dnevnoStanjeService.findByRacun(analitikaIzvoda.getRacunDuznika(),LocalDate.now());
        Racun racunKlijenta = racunService.findByRacunString(analitikaIzvoda.getRacunDuznika());
        dnevnoStanjeService.makeDnevnoStanjePosaljioca(dnevnoStanje,racunKlijenta,analitikaIzvoda);
        //////////////////////////////////////////
        //DNEVNO STANJE PRIMAOCA
        DnevnoStanje dnevnoStanjePrimaoca =dnevnoStanjeService.findByRacun(analitikaIzvoda.getRacunPrimaoca(),LocalDate.now());
        Racun racunPrimaoca = racunService.findByRacunString(analitikaIzvoda.getRacunPrimaoca());
        dnevnoStanjeService.makeDnevnoStanjePrimaoca(dnevnoStanjePrimaoca,racunPrimaoca,analitikaIzvodaPrimaoca);
        //////////////////////////////////////////
        analitikaIzvodaService.saveAnalitika(analitikaIzvodaPrimaoca);
        analitikaIzvodaService.saveAnalitika(analitikaIzvoda);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }


    @PostMapping("/izvod-racuna")
    private List<IzvodDTO> getIzvodByDates(@RequestBody UpitIzvoda upitIzvoda){

        Klijent klijent = klijentService.findOne(upitIzvoda.getId());
        Racun racun = racunService.getByKlijent(klijent);

        List<IzvodDTO> izvodDTOS = new ArrayList<>();

        for (AnalitikaIzvoda a: analitikaIzvodaService.findAll()) {
            for (DnevnoStanje dnevnoStanje:dnevnoStanjeService.findAll()){
                if(a.getDnevnoStanje().equals(dnevnoStanje)){
                    if(dnevnoStanje.getRacunPrivatnihLica().equals(racun)){
                        if(dnevnoStanje.getDatumIzvoda().isAfter(upitIzvoda.getStartDate()) && dnevnoStanje.getDatumIzvoda().isBefore(upitIzvoda.getEndDate())){
                            izvodDTOS.add(new IzvodDTO(a));
                        }
                    }
                }
            }

        }


        return izvodDTOS;



    }







}
