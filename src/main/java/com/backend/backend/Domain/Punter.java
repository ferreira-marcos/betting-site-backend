package com.backend.backend.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


// cria o dado do apostador que ficará no banco
@Entity
public class Punter {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Long cpf;

    public Long getId() {
        return id;
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
    
    
}
