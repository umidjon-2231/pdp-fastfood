package com.example.clientservice.repository;

import com.example.clientservice.entity.Filial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilialRepository extends JpaRepository<Filial, Long> {
    List<Filial> findByActiveTrue();

    Optional<Filial> findByIdAndActiveTrue(Long id);
}