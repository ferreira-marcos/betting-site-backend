package com.backend.backend.Domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Bet {
    
    //gera os IDs para os dados das apostas no banco, iniciando pelo número 1000
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @SequenceGenerator(name="seq", sequenceName="ID_SEQ", allocationSize=1, initialValue=1000)
    private Long id;
    private String numbers;

    // define a cardinalidade entre a aposta e o apostador no banco
    @ManyToOne(cascade = CascadeType.ALL) 
    private Punter punter;

    public Bet() {
    }

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

    public Punter getPunter() {
        return punter;
    }

    public void setPunter(Punter punter) {
        this.punter = punter;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bet other = (Bet) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Bet [id=" + id + ", numbers=" + numbers + ", name=" + punter.getName() + ", cpf=" + punter.getCpf() + "]";
    }
    
   
}
