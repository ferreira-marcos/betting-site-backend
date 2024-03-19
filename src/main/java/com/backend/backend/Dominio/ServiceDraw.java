package com.backend.backend.Dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;

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

    private int roundsOfDrawing;

    int j = 6;

    public int getRoundsOfDrawing(){
        return roundsOfDrawing;
    }

    public List<Integer> getNumbers(){
        return initialArray;
    }

    public void deleteAllWinners(){
        winners.clear();
        
    }

    public void deleteInitialArray(){
        initialArray.clear();
    }
    
    public void resetRoundsOfDrawing(){
        roundsOfDrawing = 0;

    }

    public Map<Integer, Integer> getAllNumbersBet(){

        List<Bet> bets = betRepository.findAll();

        Map<Integer, Integer> allNumbersBet = new HashMap<>();
        
        for (int index = 0; index < bets.size(); index++) {

            String betsNumbersString = bets.get(index).getNumbers();

            List<Integer> betNumberstInt = Arrays.stream(betsNumbersString.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            for (int j = 0; j < betNumberstInt.size(); j++) {

                if(allNumbersBet.containsKey(betNumberstInt.get(j))) {
                    allNumbersBet.put(betNumberstInt.get(j), allNumbersBet.get(betNumberstInt.get(j))+1);
                }else{

                    allNumbersBet.put(betNumberstInt.get(j), 1);
                }


            }
        }

        return allNumbersBet;

    }

    public void generateNumbers() {
            // roundsOfDrawing = 0;
            random = new Random();

            if (initialArray.isEmpty()) {
                for (int i = 0; i < 5; i++) {
                    generateOneNumber();
                }
                roundsOfDrawing++;
            }

            comparingBets();
            if (winners.isEmpty()) {
    
                while (initialArray.size() < 30) {
                    
                    if (!winners.isEmpty()) {
                        break; // Se initialArray já tem 30 elementos, não adicione mais números
                    }
                    generateOneNumber();
                    j++;
                    comparingBets();
                    roundsOfDrawing++;
                // }
            }
        }
        

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
        System.out.println("====>> "+winners);
        comparingBets();
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

        
        // itera sobre o número de apostas
        for (int index = 0; index < bets.size(); index++) {
            int numbersMatched = 0;

            // seleciona uma aposta
            String betsNumbersString = bets.get(index).getNumbers();

            // conversão dos números para inteiros
            List<Integer> betNumberstInt = Arrays.stream(betsNumbersString.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            System.out.println("--->"+ betNumberstInt);

            for (int j = 0; j < betNumberstInt.size(); j++) {

                if (initialArray.contains(betNumberstInt.get(j))) {
                    numbersMatched++;
                }

                if (numbersMatched == 5) {
                    Bet winner = bets.get(index);
                    winnerBets(bets.get(index));

                    //testar se há outro ganhador, ou seja, que tenha os mesmos números
                    for (int i = bets.indexOf(winner); i < bets.size(); i++) {
                        // isWinnersEmpty = false;
                        if(bets.get(i).getNumbers().equals(winner.getNumbers())){
                            winnerBets(bets.get(i));
                        }
                    }
                    return;
                }

            }
        }
    }

}
