package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.model.Bet;

public interface BetRepository extends JpaRepository<Bet, Integer> {
    
}
