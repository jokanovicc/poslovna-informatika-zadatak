package com.example.Banka.repository;

import com.example.Banka.model.AnalitikaIzvoda;
import com.example.Banka.model.Klijent;
import com.example.Banka.model.Racun;
import com.example.Banka.web.dto.IzvodDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AnalitikaIzvodaRepository extends JpaRepository<AnalitikaIzvoda, Integer> {

//    select * from analitika_izvoda a
//    where a.dnevno_stanje_id in ( select d.id from dnevno_stanje d where
//            d.datum_izvoda='2021-12-28' and d.racun_privatnih_lica_id = 2)


    @Query("SELECT ai.id,ai.brojStavke,ai.iznos,ai.duznik,ai.svrhaPlacanja,ai.vrstaPlacanja,ai.primalac,ai.racunDuznika,ai.racunPrimaoca,ai.model,ai.vremePrenosa,ai.pozivNaBroj,ai.hitno,ai.dnevnoStanje from AnalitikaIzvoda ai where ai.dnevnoStanje.id in (select dd.id from DnevnoStanje dd where dd.datumIzvoda >= ?1 and dd.datumIzvoda <= ?2 and dd.racunPrivatnihLica=?3)")
    List<AnalitikaIzvoda> getAnalitikaIzvodaBetweenDates(LocalDate date1, LocalDate date2, Racun racun);




}
