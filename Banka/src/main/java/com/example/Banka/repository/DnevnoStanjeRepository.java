package com.example.Banka.repository;

import com.example.Banka.model.DnevnoStanje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DnevnoStanjeRepository extends JpaRepository<DnevnoStanje, Integer> {

    DnevnoStanje findByRacunPrivatnihLicaBrojRacunaAndAndDatumIzvoda(String racun, LocalDate datum);

}
