package com.example.Banka.repository;

import com.example.Banka.model.Banka;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankaRepository extends JpaRepository<Banka, Integer> {
}
