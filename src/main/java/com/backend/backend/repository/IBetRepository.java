package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.Domain.Bet;

// extende a interface JpaRepository
public interface IBetRepository extends JpaRepository<Bet, Long> {
    
}
