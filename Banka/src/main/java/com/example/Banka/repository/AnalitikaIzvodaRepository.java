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

}
