package com.backend.backend.Dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


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

    @Override
    public String toString() {
        return "Punter [id=" + id + ", name=" + name + ", cpf=" + cpf + "]";
    }

    
    
}
