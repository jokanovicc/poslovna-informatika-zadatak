package com.example.Banka.repository;

import com.example.Banka.model.AnalitikaIzvoda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnalitikaIzvodaRepository extends JpaRepository<AnalitikaIzvoda, Integer> {

//    select * from analitika_izvoda a
//    where a.dnevno_stanje_id in ( select d.id from dnevno_stanje d where
//            d.datum_izvoda='2021-12-28' and d.racun_privatnih_lica_id = 2)

}
