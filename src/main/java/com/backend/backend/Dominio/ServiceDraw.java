package com.backend.backend.Dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.backend.backend.repository.IBetRepository;

public class ServiceDraw {

    IBetRepository betRepository;

    Random random;
    private ArrayList<Integer> initialArray = new ArrayList<>();
    private ArrayList<Bet> winners = new ArrayList<>();

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

    public List<Bet> getWinners(){
        return winners;
    }


    public void winnerBets(Bet bet){

        if(!winners.contains(bet)){
            winners.add(bet);

        }
    }


    public void comparingBets() {

        //recupera a lista de apostas do banco
        List<Bet> bets = betRepository.findAll();
        
        int  numbersMatched = 0;
        
        //itera sobre o número de apostas
        for (int index = 0; index < bets.size(); index++) {

            //seleciona uma aposta
            String betsNumbersString = bets.get(index).getNumbers();
            
            //conversão dos números para inteiros
            List<Integer> betNumberstInt = Arrays.stream(betsNumbersString.split(","))
                                            .map(Integer::parseInt)
                                            .collect(Collectors.toList());

            for (int j = 0; j < betNumberstInt.size(); j++) {

                if(initialArray.contains(betNumberstInt.get(j))){
                    numbersMatched++;
                }
                if(numbersMatched == 5){
                    winnerBets(bets.get(index));
                }
            }
        }
    }

}
