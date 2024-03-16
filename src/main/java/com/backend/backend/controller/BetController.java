package com.backend.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.backend.backend.model.Bet;
// import com.backend.backend.model.Punter;
import com.backend.backend.repository.BetRepository;
// import com.backend.backend.repository.PunterRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class BetController {

    @Autowired
    private BetRepository betRepository;

    @PostMapping("/newBet")
    Bet newBet(@RequestBody Bet bet){
        return betRepository.save(bet);

    }

    @GetMapping("/allbets")
    List<Bet> getAllBets() {
        return betRepository.findAll();
    }

    @GetMapping("/bet/{id}")
    Bet getBetById(@PathVariable Long id) {
        return betRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

}
