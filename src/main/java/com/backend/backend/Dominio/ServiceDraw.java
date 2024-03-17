package com.backend.backend.Dominio;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.backend.backend.repository.IBetRepository;

public class ServiceDraw {

    IBetRepository betRepository;

    Random random;
    ArrayList<Integer> initialArray = new ArrayList<>();

    @Autowired
    public ServiceDraw(IBetRepository betRepository) {
        this.betRepository = betRepository;
    }

    // gera o array inicial de números que serão usados no sorteio
    public ArrayList<Integer> generateInitialNumbers() {
        random = new Random();
        int count = 5;

        for (int i = 0; i < count; i++) {
            int number = random.nextInt(1, 51);

            // se sertificar que não sejam gerados números repetidos
            if (!initialArray.contains(number)) {
                initialArray.add(number);
            } else {
                count++;
            }
        }
        return initialArray;

    }

    public int generateOneNumber() {
        
        int number = random.nextInt(1, 51);
        //garantir que o valor gerado aleatóriamente não esteja presente no array
        while (initialArray.contains(number)) {
            number = random.nextInt(1, 51);
        }
        initialArray.add(number);

        return number;
    }

    public Bet comparingBets() {
        List<Bet> bets = betRepository.findAll();

        
        
    }

}
