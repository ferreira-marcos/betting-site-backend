package com.backend.backend.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.backend.Domain.Bet;
import com.backend.backend.Domain.ServiceDraw;
import com.backend.backend.repository.IBetRepository;


@RestController
@CrossOrigin("http://localhost:3000")
public class DrawController {
    
    private final ServiceDraw serviceDraw;
    @Autowired
    private IBetRepository betRepository;

    @Autowired
    public DrawController(ServiceDraw serviceDraw) {
        this.serviceDraw = serviceDraw;
    }

    @GetMapping("/drawWinners")
    List<Bet> winners(){
        return serviceDraw.getWinners();
    }

    @GetMapping("/generateNumbers")
    void generateNumbers(){
        serviceDraw.generateNumbers();
    }

    @GetMapping("/getNumbers")
    List<Integer> getGenerateNumbers(){
        return serviceDraw.getNumbers();
    }

    @GetMapping("/getRoundsOfDrawing")
    int getRoundsOfDrawing(){
        return serviceDraw.getRoundsOfDrawing();
    }

    @DeleteMapping("/deleteDB")
    String eraseBetsDB(){
        betRepository.deleteAll();
        serviceDraw.deleteAllWinners();
        serviceDraw.deleteInitialArray();
        serviceDraw.resetRoundsOfDrawing();
        return  "repositório deletado";
    }

    @GetMapping("/getBetsNumbers")
    Map<Integer, Integer> getAllNumbersBet(){
       
        return serviceDraw.getAllNumbersBet();
    }

    @GetMapping("/calulateDiscount")
    int calulateDiscount(){
        return serviceDraw.calulateDiscount();
    }


}
