package com.backend.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.backend.backend.Domain.Bet;
import com.backend.backend.Domain.ServiceBet;
import com.backend.backend.repository.IBetRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class BetController {

    private IBetRepository betRepository;   
    private ServiceBet serviceBet;


    @Autowired
    public BetController(IBetRepository betRepository, ServiceBet serviceBet) {
        this.betRepository = betRepository;
        this.serviceBet = serviceBet;
    }

    @PostMapping("/newBet")
    Bet newBet(@RequestBody Bet bet){
        return serviceBet.createBet(bet);
    }

    @GetMapping("/allbets")
    List<Bet> getAllBets() {
        return betRepository.findAll();
    }



}
