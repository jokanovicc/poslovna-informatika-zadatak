package com.example.Banka.web;

import com.example.Banka.model.AnalitikaIzvoda;
import com.example.Banka.model.DnevnoStanje;
import com.example.Banka.model.Racun;
import com.example.Banka.service.AnalitikaIzvodaService;
import com.example.Banka.service.DnevnoStanjeService;
import com.example.Banka.service.RacunService;
import com.example.Banka.web.dto.NalogZaPrenosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/analitike-izvoda")
public class PrenosNovcaController {



    @Autowired
    private AnalitikaIzvodaService analitikaIzvodaService;

    @Autowired
    private RacunService racunService;

    @Autowired
    private DnevnoStanjeService dnevnoStanjeService;


    @PostMapping(value = "nalog-za-prenos")
    public ResponseEntity<Void> nalogZaPrenos(@RequestBody NalogZaPrenosDTO nalogZaPrenosDTO){
        AnalitikaIzvoda analitikaIzvoda = new AnalitikaIzvoda();
        Random r = new Random();
        int low = 10;
        int high = 1000;
        int result = r.nextInt(high-low) + low;


        analitikaIzvoda.setBrojStavke(result);
        analitikaIzvoda.setDuznik(nalogZaPrenosDTO.getUplatilac());
        analitikaIzvoda.setPrimalac("primalac opisan po računu");
        analitikaIzvoda.setHitno(nalogZaPrenosDTO.isHitno());
        analitikaIzvoda.setModel(98);
        analitikaIzvoda.setPozivNaBroj("transakcija građana");
        analitikaIzvoda.setIznos(nalogZaPrenosDTO.getIznos());
        analitikaIzvoda.setVrstaPlacanja("transkacija po nalogu građana");
        analitikaIzvoda.setRacunDuznika(nalogZaPrenosDTO.getBrojRacunaUplatioca());
        analitikaIzvoda.setRacunPrimaoca(nalogZaPrenosDTO.getBrojRacunaPrimaoca());
        analitikaIzvoda.setVremePrenosa(LocalDate.now());
        analitikaIzvoda.setSvrhaPlacanja(nalogZaPrenosDTO.getSvrhaPlacanja());

        if(racunService.poklapanjeBanki(nalogZaPrenosDTO.getBrojRacunaPrimaoca(),nalogZaPrenosDTO.getBrojRacunaUplatioca())){
            System.out.println("Nema ni RTGS ni Kliringa, novac je prenesen a ni porukica");
        }else {
            System.out.println("Različite banke - RTGS ili Kliring");
            //CASE 1 - RTGS
            if(nalogZaPrenosDTO.isHitno() || nalogZaPrenosDTO.getIznos() > 300000){

                System.out.println("-------REALTIME MT102-----------");
                Map<Integer, AnalitikaIzvoda> ret = new HashMap<>();
                List<AnalitikaIzvoda> analitike = analitikaIzvodaService.findAll();
                ret.put(analitikaIzvoda.getBrojStavke(),analitikaIzvoda);
                analitikaIzvodaService.saveToFile103(ret);

            }else{
                System.out.println("-------KLIRING MT103-----------");

                Map<Integer, AnalitikaIzvoda> ret2 = new HashMap<>();
                List<AnalitikaIzvoda> analitike2 = analitikaIzvodaService.findAll();
                for(AnalitikaIzvoda analitikaIzvoda1: analitike2){
                    if(analitikaIzvoda1.getHitno()==false || analitikaIzvoda1.getIznos() < 300000 && analitikaIzvoda1.getVremePrenosa() == analitikaIzvoda.getVremePrenosa())
                        ret2.put(analitikaIzvoda1.getBrojStavke(),analitikaIzvoda1);

                }
                ret2.put(analitikaIzvoda.getBrojStavke(),analitikaIzvoda);
                analitikaIzvodaService.saveToFile102(ret2,result);


            }

        }

        AnalitikaIzvoda analitikaIzvodaPrimaoca = analitikaIzvodaService.makeAnalitikaIzvodaPrimaoca(analitikaIzvoda);


        //DNEVNO STANJE UPLATIOCA
        DnevnoStanje dnevnoStanje =dnevnoStanjeService.findByRacun(analitikaIzvoda.getRacunDuznika(),LocalDate.now());
        Racun racunKlijenta = racunService.findByRacunString(analitikaIzvoda.getRacunDuznika());
        System.out.println(dnevnoStanje);
        if(dnevnoStanje !=null){
            System.out.println("VEC IMA DNEVNO STANJE");
            dnevnoStanje.setPrethodnoStanje(dnevnoStanje.getNovoStanje());
            dnevnoStanje.setNovoStanje(dnevnoStanje.getNovoStanje()-analitikaIzvoda.getIznos());
            dnevnoStanje.setPrometUKorist(dnevnoStanje.getPrometUKorist());
            dnevnoStanje.setPrometNaTeret(dnevnoStanje.getPrometNaTeret()+analitikaIzvoda.getIznos());
            dnevnoStanjeService.save(dnevnoStanje);
            analitikaIzvoda.setDnevnoStanje(dnevnoStanje);
            analitikaIzvodaService.saveAnalitika(analitikaIzvoda);




        }else if(dnevnoStanje==null){
            System.out.println("NEMA DNEVNO STANJE");
            DnevnoStanje novoDnevnoStanje = new DnevnoStanje();
            novoDnevnoStanje.setDatumIzvoda(LocalDate.now());
            novoDnevnoStanje.setPrometNaTeret(analitikaIzvoda.getIznos());
            novoDnevnoStanje.setRacunPrivatnihLica(racunKlijenta);
            novoDnevnoStanje.setRezervisano(0.0);
            novoDnevnoStanje.setBrojIzvoda(r.nextInt(high-low) + low);
            novoDnevnoStanje.setPrometUKorist(0.0);
            novoDnevnoStanje.setNovoStanje(-analitikaIzvoda.getIznos());
            novoDnevnoStanje.setPrethodnoStanje(0.0);
            novoDnevnoStanje.setBrojIzvoda(result);
            dnevnoStanjeService.save(novoDnevnoStanje);

            analitikaIzvoda.setDnevnoStanje(novoDnevnoStanje);

        }

        //////////////////////////////////////////


        //DNEVNO STANJE PRIMAOCA
        DnevnoStanje dnevnoStanjePrimaoca =dnevnoStanjeService.findByRacun(analitikaIzvoda.getRacunPrimaoca(),LocalDate.now());
        Racun racunPrimaoca = racunService.findByRacunString(analitikaIzvoda.getRacunPrimaoca());
        if(dnevnoStanjePrimaoca !=null){
            System.out.println("VEC IMA DNEVNO STANJE");
            dnevnoStanjePrimaoca.setPrethodnoStanje(dnevnoStanjePrimaoca.getNovoStanje());
            dnevnoStanjePrimaoca.setNovoStanje(dnevnoStanjePrimaoca.getNovoStanje()+analitikaIzvoda.getIznos());
            dnevnoStanjePrimaoca.setPrometUKorist(dnevnoStanjePrimaoca.getPrometUKorist() + analitikaIzvoda.getIznos());
            dnevnoStanjePrimaoca.setPrometNaTeret(dnevnoStanjePrimaoca.getPrometNaTeret());
            dnevnoStanjeService.save(dnevnoStanjePrimaoca);
            analitikaIzvodaPrimaoca.setDnevnoStanje(dnevnoStanjePrimaoca);





        }else if(dnevnoStanjePrimaoca==null){
            System.out.println("NEMA DNEVNO STANJE");
            DnevnoStanje novoDnevnoStanjePrimaoca = new DnevnoStanje();
            novoDnevnoStanjePrimaoca.setDatumIzvoda(LocalDate.now());
            novoDnevnoStanjePrimaoca.setPrometNaTeret(0.0);
            novoDnevnoStanjePrimaoca.setRacunPrivatnihLica(racunPrimaoca);
            novoDnevnoStanjePrimaoca.setRezervisano(0.0);
            novoDnevnoStanjePrimaoca.setBrojIzvoda(r.nextInt(high-low) + low);
            novoDnevnoStanjePrimaoca.setPrometUKorist(analitikaIzvodaPrimaoca.getIznos());
            novoDnevnoStanjePrimaoca.setNovoStanje(analitikaIzvoda.getIznos());
            novoDnevnoStanjePrimaoca.setPrethodnoStanje(0.0);
            dnevnoStanjeService.save(novoDnevnoStanjePrimaoca);
            analitikaIzvodaPrimaoca.setDnevnoStanje(novoDnevnoStanjePrimaoca);


        }

        //////////////////////////////////////////


        analitikaIzvodaService.saveAnalitika(analitikaIzvodaPrimaoca);
        analitikaIzvodaService.saveAnalitika(analitikaIzvoda);


        return new ResponseEntity<Void>(HttpStatus.OK);

    }


}
