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


    // private boolean isAddedNumber = false;
    private int roundsOfDrawing = 0;

    int j = 6;
    // boolean isWinnersEmpty = true;  

    public int getRoundsOfDrawing(){
        return roundsOfDrawing;
    }

    public List<Integer> getNumbers(){
        // System.out.println("===================================================>>>>>>>>>>>>>>>>>>>>>>"+initialArray);
        return initialArray;
    }

    boolean isAddedNumber = false;

    public void generateNumbers() {

                if (initialArray.isEmpty()) {
                
                    for (int i = 0; i < 5; i++) {
                        // generateOneNumber();
                        initialArray.add(i+1);
                        // System.out.println("---" + initialArray);
                    }
                    roundsOfDrawing++;
                    
                }
                

                while(winners.isEmpty() && isAddedNumber){
                    
                    if (initialArray.size() >= 5) {
                        // generateOneNumber();
                        System.out.println("===================>"+roundsOfDrawing);
                        initialArray.add(j);
                        j++;
                        roundsOfDrawing++;
                        // isAddedNumber = true;
                    } 
            
                    // else if (isAddedNumber) {
                    //     isAddedNumber = false;
                    // }
                    comparingBets();
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
        System.out.println("===================================================>>>>>>>>>>>>>>>>>>>>>>"+initialArray);

        // recupera a lista de apostas do banco
        List<Bet> bets = betRepository.findAll();

        
        // itera sobre o número de apostas
        for (int index = 0; index < bets.size(); index++) {
            int numbersMatched = 0;
            // System.out.println("--------------------"+bets.get(index));

            // seleciona uma aposta
            String betsNumbersString = bets.get(index).getNumbers();

            // conversão dos números para inteiros
            List<Integer> betNumberstInt = Arrays.stream(betsNumbersString.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            System.out.println("--->"+ betNumberstInt);

            for (int j = 0; j < betNumberstInt.size(); j++) {

                // System.out.println("iterações:"+j);
                if (initialArray.contains(betNumberstInt.get(j))) {
                    System.out.println("xxxx>"+ bets.get(index));
                    // System.out.println("----------------------numberMatched:"+numbersMatched);
                    numbersMatched++;
                }

                if (numbersMatched == 5) {
                    System.out.println("====>"+ bets.get(index));
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
                // else {
                //     generateNumbers();
                // }
            }
        }
        // generateNumbers();
    }

}
