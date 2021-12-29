package com.example.Banka.service;

import com.example.Banka.model.AnalitikaIzvoda;
import com.example.Banka.model.DnevnoStanje;
import com.example.Banka.model.Racun;
import com.example.Banka.repository.DnevnoStanjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class DnevnoStanjeService {

    @Autowired
    private DnevnoStanjeRepository dnevnoStanjeRepository;

    @Autowired
    private AnalitikaIzvodaService analitikaIzvodaService;



    public DnevnoStanje save(DnevnoStanje dnevnoStanje){
        return dnevnoStanjeRepository.save(dnevnoStanje);
    }

    public DnevnoStanje findByRacun(String racun, LocalDate date){
        return dnevnoStanjeRepository.findByRacunPrivatnihLicaBrojRacunaAndAndDatumIzvoda(racun,date);
    }

    public List<DnevnoStanje> findAll(){
        return dnevnoStanjeRepository.findAll();
    }

    public void makeDnevnoStanjePosaljioca(DnevnoStanje dnevnoStanje, Racun racunKlijenta, AnalitikaIzvoda analitikaIzvoda){

        Random r = new Random();
        int low = 10;
        int high = 1000;
        int result = r.nextInt(high-low) + low;

        if(dnevnoStanje !=null){
            System.out.println("Dužnik već ima dnevno stanje\nAžuriranje...");
            dnevnoStanje.setPrethodnoStanje(dnevnoStanje.getNovoStanje());
            dnevnoStanje.setNovoStanje(dnevnoStanje.getNovoStanje()-analitikaIzvoda.getIznos());
            dnevnoStanje.setPrometUKorist(dnevnoStanje.getPrometUKorist());
            dnevnoStanje.setPrometNaTeret(dnevnoStanje.getPrometNaTeret()+analitikaIzvoda.getIznos());
            save(dnevnoStanje);
            analitikaIzvoda.setDnevnoStanje(dnevnoStanje);
            analitikaIzvodaService.saveAnalitika(analitikaIzvoda);


        }else{
            System.out.println("Dužniku se formira novo dnevno stanje\nAžuriranje...");
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
            save(novoDnevnoStanje);
            analitikaIzvoda.setDnevnoStanje(novoDnevnoStanje);

        }


    }

    public void makeDnevnoStanjePrimaoca(DnevnoStanje dnevnoStanjePrimaoca, Racun racunPrimaoca, AnalitikaIzvoda analitikaIzvodaPrimaoca){
        Random r = new Random();
        int low = 10;
        int high = 1000;
        int result = r.nextInt(high-low) + low;
        if(dnevnoStanjePrimaoca !=null){
            System.out.println("Poveriocu već ima dnevno stanje\nAžuriranje...");
            dnevnoStanjePrimaoca.setPrethodnoStanje(dnevnoStanjePrimaoca.getNovoStanje());
            dnevnoStanjePrimaoca.setNovoStanje(dnevnoStanjePrimaoca.getNovoStanje()+analitikaIzvodaPrimaoca.getIznos());
            dnevnoStanjePrimaoca.setPrometUKorist(dnevnoStanjePrimaoca.getPrometUKorist() + analitikaIzvodaPrimaoca.getIznos());
            dnevnoStanjePrimaoca.setPrometNaTeret(dnevnoStanjePrimaoca.getPrometNaTeret());
            save(dnevnoStanjePrimaoca);
            analitikaIzvodaPrimaoca.setDnevnoStanje(dnevnoStanjePrimaoca);



        }else if(dnevnoStanjePrimaoca==null){
            System.out.println("Poveriocu se formira novo dnevno stanje\nAžuriranje...");
            DnevnoStanje novoDnevnoStanjePrimaoca = new DnevnoStanje();
            novoDnevnoStanjePrimaoca.setDatumIzvoda(LocalDate.now());
            novoDnevnoStanjePrimaoca.setPrometNaTeret(0.0);
            novoDnevnoStanjePrimaoca.setRacunPrivatnihLica(racunPrimaoca);
            novoDnevnoStanjePrimaoca.setRezervisano(0.0);
            novoDnevnoStanjePrimaoca.setBrojIzvoda(r.nextInt(high-low) + low);
            novoDnevnoStanjePrimaoca.setPrometUKorist(analitikaIzvodaPrimaoca.getIznos());
            novoDnevnoStanjePrimaoca.setNovoStanje(analitikaIzvodaPrimaoca.getIznos());
            novoDnevnoStanjePrimaoca.setPrethodnoStanje(0.0);
            save(novoDnevnoStanjePrimaoca);
            analitikaIzvodaPrimaoca.setDnevnoStanje(novoDnevnoStanjePrimaoca);


        }


    }

}
