package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.Dominio.Punter;

public interface PunterRepository extends JpaRepository<Punter, Long> {
    
}
