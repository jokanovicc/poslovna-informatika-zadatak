package com.example.Banka.repository;

import com.example.Banka.model.Klijent;
import com.example.Banka.model.Racun;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RacunRepository extends JpaRepository<Racun, Integer> {

    Racun findByBrojRacuna(String brojRacuna);

    Racun findByKlijent(Klijent klijent);

}
