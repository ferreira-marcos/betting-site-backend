package com.backend.backend.Domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.backend.backend.repository.IBetRepository;
import com.backend.backend.repository.IPunterRepository;

@Component
public class ServiceBet {

    IBetRepository betRepository;
    IPunterRepository punterRepository;

    @Autowired
    public ServiceBet(IBetRepository betRepository, IPunterRepository punterRepository) {
        this.betRepository = betRepository;
        this.punterRepository = punterRepository;
    }

   public Bet createBet(Bet bet){

    Punter punter = bet.getPunter();
    String numbers = bet.getNumbers();
    Bet createdBet;

    // verifica se já não existe um apostados com o mesmo CPF
    if(punterRepository.existsById(punter.getCpf())){
        createdBet = new Bet(punterRepository.findByCpf(punter.getCpf()), numbers);
    }else{
        createdBet = new Bet(punter, numbers);
    }
    betRepository.save(createdBet);
    return bet;

   }
    
    
}
