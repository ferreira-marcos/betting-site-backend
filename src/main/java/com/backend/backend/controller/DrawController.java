package com.backend.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.Dominio.Bet;
import com.backend.backend.Dominio.ServiceDraw;
import com.backend.backend.repository.IBetRepository;


@RestController
@CrossOrigin("http://localhost:3000")
public class DrawController {
    

    @Autowired
    private IBetRepository betRepository;


    private final ServiceDraw serviceDraw;

    @Autowired
    public DrawController(ServiceDraw serviceDraw) {
        this.serviceDraw = serviceDraw;
    }

    @GetMapping("/drawWinners")
    List<Bet> winners(){
        return serviceDraw.getWinners();
    }

    @GetMapping("/getNumbers")
    List<Integer> getGenerateNumbers(){
        return serviceDraw.generateNumbers();
    }




}