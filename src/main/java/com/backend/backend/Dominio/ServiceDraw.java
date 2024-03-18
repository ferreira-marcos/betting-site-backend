package com.backend.backend.Dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.backend.repository.IBetRepository;

@Component
public class ServiceDraw {

    IBetRepository betRepository;

    Random random;
    private List<Integer> initialArray = new ArrayList<>();
    private ArrayList<Bet> winners = new ArrayList<>();

    @Autowired
    public ServiceDraw(IBetRepository betRepository) {
        this.betRepository = betRepository;
    }


    private boolean isAddedNumber = false;

    public List<Integer> generateNumbers() {
        if(initialArray.size() <30){

            if (initialArray.isEmpty()) {
                
                for (int i = 0; i < 4; i++) {
                    generateOneNumber();
                    System.out.println("---" + initialArray);
                }
            }
    
            if (initialArray.size() >= 4 && !isAddedNumber) {
                generateOneNumber();
                System.out.println("===========>" + initialArray);
                isAddedNumber = true;
            } 
    
            else if (isAddedNumber) {
                isAddedNumber = false;
            }
        }
        

        return initialArray;
    }

    public void generateOneNumber() {
        random = new Random();
        int number = random.nextInt(1, 51);
        // garantir que o valor gerado aleatoriamente não esteja presente no array
        while (initialArray.contains(number)) {
            number = random.nextInt(1, 51);
        }

        initialArray.add(number);
    }

    public List<Bet> getWinners() {
        return winners;
    }

    public void winnerBets(Bet bet) {

        if (!winners.contains(bet)) {
            winners.add(bet);

        }
    }

    public void comparingBets() {

        // recupera a lista de apostas do banco
        List<Bet> bets = betRepository.findAll();

        int numbersMatched = 0;

        // itera sobre o número de apostas
        for (int index = 0; index < bets.size(); index++) {

            // seleciona uma aposta
            String betsNumbersString = bets.get(index).getNumbers();

            // conversão dos números para inteiros
            List<Integer> betNumberstInt = Arrays.stream(betsNumbersString.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            for (int j = 0; j < betNumberstInt.size(); j++) {

                if (initialArray.contains(betNumberstInt.get(j))) {
                    numbersMatched++;
                }
                if (numbersMatched == 5) {
                    winnerBets(bets.get(index));
                }
            }
        }
    }

}
