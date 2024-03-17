package com.backend.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.backend.backend.Dominio.Bet;
import com.backend.backend.Dominio.ServiceDraw;
import com.backend.backend.repository.IBetRepository;

public class DrawController {
    

     @Autowired
    private IBetRepository betRepository;

    ServiceDraw serviceDraw;

    @GetMapping("/drawWinners")
    List<Bet> winners(){
        return serviceDraw.getWinners();
    }
}
