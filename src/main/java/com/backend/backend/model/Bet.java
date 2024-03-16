package com.backend.backend.model;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Bet {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @SequenceGenerator(name="seq", sequenceName="ID_SEQ", allocationSize=1, initialValue=1000)
    private Integer id;

    // @Autowired
    @ManyToOne
    private Punter punter;

    private String numbers;

    
    public Bet(Punter punter, String numbers) {
        this.punter = punter;
        this.numbers = numbers;
    }

    public Bet(){
        
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Punter getPunter() {
        return punter;
    }


    public void setPunter(Punter punter) {
        this.punter = punter;
    }


    public String getNumbers() {
        return numbers;
    }


    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    

}
