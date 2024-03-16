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
    private Long id;

    private String name;
    private Long cpf;
    private String numbers;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNumbers() {
        return numbers;
    }
    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getCpf() {
        return cpf;
    }
    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }
    
    
    @Override
    public String toString() {
        return "Bet [id=" + id + ", numbers=" + numbers + ", name=" + name + ", cpf=" + cpf + "]";
    }



    
   
}
