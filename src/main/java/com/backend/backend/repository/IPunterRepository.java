package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.Domain.Punter;

// extende a interface JpaRepository
public interface IPunterRepository extends JpaRepository<Punter, Long> {
    Punter findByCpf(Long cpf);
    
}
