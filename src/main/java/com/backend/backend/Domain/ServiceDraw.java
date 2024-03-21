package com.backend.backend.Domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    // faz a injeção de dependência
    @Autowired
    public ServiceDraw(IBetRepository betRepository) {
        this.betRepository = betRepository;
    }

    // contador de quantas rodadas o sorteio executa
    private int roundsOfDrawing;

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

    // calcula desconto recebido pelo vencedor
    public int calulateDiscount(){
        int discountDistribution = 50 / winners.size();
        return discountDistribution;

    }


    // calcula a quantidade de apostas em que números escolhidos estão
    public Map<Integer, Integer> getAllNumbersBet(){
        List<Bet> bets = betRepository.findAll();
        Map<Integer, Integer> allNumbersBet = new HashMap<>();
        
        for (int index = 0; index < bets.size(); index++) {

            String betsNumbersString = bets.get(index).getNumbers();

            List<Integer> betNumberstInt = convertListStringToInt(betsNumbersString);

            for (int j = 0; j < betNumberstInt.size(); j++) {

                // caso o número já tenha sido contabilizado antes, soma sua quantidade
                if(allNumbersBet.containsKey(betNumberstInt.get(j))) {
                    allNumbersBet.put(betNumberstInt.get(j), allNumbersBet.get(betNumberstInt.get(j))+1);
                 
                 // caso seja um número que ainda não tenha sido contabilizado
                }else{
                    allNumbersBet.put(betNumberstInt.get(j), 1);
                }
            }
        }

        return allNumbersBet;

    }

    // gera os números sorteados aleatóriamente
    public void generateNumbers() {
            random = new Random();

            // gera os primeiros 5 números do sorteio
            if (initialArray.isEmpty()) {
                for (int i = 0; i < 5; i++) {
                    generateOneNumber();
            
                }
                // incrementa o contador de rodadas
                roundsOfDrawing++;
            }

            // chama o método que verifica se os números gerados foram escolhidos por um jogador
            comparingBets();

            // se não houver vencedores
            if (winners.isEmpty()) {
                
                // inicia a geração dospróximos números da rodada até terem no máximo 30 números
                while (initialArray.size() < 30) {
                    
                    // caso haja um vencedos, a geração de números é interrompida
                    if (!winners.isEmpty()) {
                        break; 
                    }
                    generateOneNumber();
                    comparingBets();

                    // incrementa o contador de rodadas
                    roundsOfDrawing++;
            }
        }
        

    }

    // gera um número de forma aleatória
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
        comparingBets();
        return winners;
    }

    public void winnerBets(Bet bet) {
        if (!winners.contains(bet)) {
            winners.add(bet);

        }
    }

    public List<Integer> convertListStringToInt(String valueToConvert){

        List<Integer> convertedValue = Arrays.stream(valueToConvert.split(","))
        .map(Integer::parseInt)
        .collect(Collectors.toList());

        return convertedValue;
    }


    public void comparingBets() {

        // recupera a lista de apostas do banco
        List<Bet> bets = betRepository.findAll();

        // itera sobre o número de apostas
        for (int index = 0; index < bets.size(); index++) {
            int numbersMatched = 0;

            // seleciona uma aposta
            String betsNumbersString = bets.get(index).getNumbers();

            List<Integer> betNumberstInt = convertListStringToInt(betsNumbersString);

            for (int j = 0; j < betNumberstInt.size(); j++) {

                // testa se há os números da aposta é um sorteado
                if (initialArray.contains(betNumberstInt.get(j))) {
                    numbersMatched++;
                }

                // quando os 5 números da aposta estão entre os sorteados é cosiderada uma aposta vencedora
                if (numbersMatched == 5) {
                    winnerBets(bets.get(index));

                }

            }
        }
    }

}
